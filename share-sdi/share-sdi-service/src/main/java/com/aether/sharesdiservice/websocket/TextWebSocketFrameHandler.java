package com.aether.sharesdiservice.websocket;

import com.aether.ssltestserver.entity.TRemotecmdInfo;
import com.aether.ssltestserver.enums.PushMsgTypeEnum;
import com.aether.ssltestserver.enums.UsageMessageType;
import com.aether.ssltestserver.module.Message;
import com.aether.ssltestserver.module.RespMessage;
import com.aether.ssltestserver.service.TRemotecmdInfoService;
import com.aether.ssltestserver.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	private Logger loger = LoggerFactory.getLogger(TextWebSocketFrameHandler.class);


	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private ChannelGroup group;
	private TRemotecmdInfoService tRemotecmdInfoService;
	private RedisUtil redisUtil;
	private String sdi_host;
	private String STAInfo_redisKey;
	
	public TextWebSocketFrameHandler(ChannelGroup group, RedisUtil redisUtil,TRemotecmdInfoService tRemotecmdInfoService,String sdi_host,String STAInfo_redisKey) {
		this.group = group;
		this.redisUtil = redisUtil;
		this.sdi_host = sdi_host;
		this.STAInfo_redisKey = STAInfo_redisKey;
		this.tRemotecmdInfoService = tRemotecmdInfoService;
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		loger.info("Event====>{}", evt);
		
		if(evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
			ctx.pipeline().remove(HttpRequestHandler.class);
			//加入当前用户中去
			Channel channel = ctx.channel();
			String  token= channel.attr(WSConstants.CHANNEL_TOKEN_KEY).get();
			group.add(channel);
			WSConstants.addChannels(token, ctx.channel());
			//存入redis 以set结构
			redisUtil.hset(sdi_host, token, sdf.format(new Date()));
			loger.info("终端：{} 上线。。channelId:{}",token,channel.id().asLongText());
			//发送该终端对应的远程指令集
			String brand = null,sysVersion = null,uiVersion = null;
			//搜索对应远程指令集
			TRemotecmdInfo tRemotecmdInfo = tRemotecmdInfoService.queryByBrandAndSysVersionAndUIVersion(brand, sysVersion, uiVersion);
			//需要推送
			Message message = new Message(tRemotecmdInfo, UsageMessageType.REMOTE_CMDS);
			message.setPushMsgType(PushMsgTypeEnum.getCode(PushMsgTypeEnum.DEVICE_AP));
			try {
				if (channel != null){
					channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message, SerializerFeature.DisableCircularReferenceDetect)));
					WSConstants.sentMsgs.putIfAbsent(message.getMsgId(),message);
					loger.info("远程指令集下发成功}{}--->{}",token,message);
				}else {
					loger.error("远程指令集下发失败 未找到终端:{}",sdi_host,token);
				}
			} catch (Exception ex) {
				loger.error("远程指令集下发失败：Target{};message:{};detail:{}", token, tRemotecmdInfo, ex.getMessage());
			}
		}else {
			super.userEventTriggered(ctx, evt);
		}
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		//不接受客户端发送的数据
		String token = ctx.channel().attr(WSConstants.CHANNEL_TOKEN_KEY).get();
		String text = msg.text();
		RespMessage respMessage = JSONObject.parseObject(text, RespMessage.class);
		String msgId = respMessage.getMsgId();
		Message remove = WSConstants.sentMsgs.remove(msgId);
		if (remove != null){
			int code = respMessage.getCode();
			if (code == 200){
				//终端设备接收消息成功
				loger.info("终端设备{} 接收消息成功",token);
			}else loger.error("终端设备{} 接收消息失败:{}",token,respMessage.getMsg());
		}{
			//无效响应消息，需要忽略
		}
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		loger.info("Current channel channelInactive");
		offlines(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		loger.info("Current channel handlerRemoved");
		offlines(ctx);
	}
	
	private void offlines(ChannelHandlerContext ctx) {
		Channel channel = ctx.channel();
		String token = channel.attr(WSConstants.CHANNEL_TOKEN_KEY).get();
		WSConstants.removeChannels(channel,redisUtil,sdi_host,STAInfo_redisKey);
		group.remove(channel);
		ctx.close();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		loger.error("TextWebSocketFrameHandler exceptionCaught Throwable cause.getMessage()={}", cause.getMessage());
		offlines(ctx);
	}
}
