package com.aehter.sharenettyservice.websocket;

import com.aehter.sharenettyservice.entity.TDeviceFlux;
import com.aehter.sharenettyservice.entity.TGpsHis;
import com.aehter.sharenettyservice.entity.TGpsHisExtra;
import com.aehter.sharenettyservice.entity.TRemotecmdInfo;
import com.aehter.sharenettyservice.enums.MessageType;
import com.aehter.sharenettyservice.enums.UsageMessageType;
import com.aehter.sharenettyservice.service.TDeviceFluxService;
import com.aehter.sharenettyservice.service.TGpsHisService;
import com.aehter.sharenettyservice.service.TRemotecmdInfoService;
import com.aether.sharecommon.utils.RedisUtil;
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
import java.util.HashMap;
import java.util.Map;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private Logger loger = LoggerFactory.getLogger(TextWebSocketFrameHandler.class);


    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ChannelGroup group;
    private RedisUtil redisUtil;
    private TRemotecmdInfoService tRemotecmdInfoService;
    private TDeviceFluxService tDeviceFluxService;
    private TGpsHisService tGpsHisService;

    private String sdi_host;
    private String netty_web_host;
    private String netty_socket_host;
    private String netty_redisKey;
    private String STAInfo_redisKey;

    public TextWebSocketFrameHandler(ChannelGroup group,
                                     RedisUtil redisUtil,
                                     TRemotecmdInfoService tRemotecmdInfoService,
                                     TDeviceFluxService tDeviceFluxService,
                                     TGpsHisService tGpsHisService,
                                     String sdi_host,
                                     String netty_web_host,
                                     String netty_socket_host,
                                     String netty_redisKey,
                                     String STAInfo_redisKey) {
        this.group = group;
        this.redisUtil = redisUtil;
        this.tRemotecmdInfoService = tRemotecmdInfoService;
        this.tDeviceFluxService = tDeviceFluxService;
        this.tGpsHisService = tGpsHisService;
        this.sdi_host = sdi_host;
        this.netty_web_host = netty_web_host;
        this.netty_socket_host = netty_socket_host;
        this.netty_redisKey = netty_redisKey;
        this.STAInfo_redisKey = STAInfo_redisKey;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        loger.info("Event====>{}", evt);

        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            ctx.pipeline().remove(HttpRequestHandler.class);
            //加入当前用户中去
            Channel channel = ctx.channel();
            String token = channel.attr(WSConstants.CHANNEL_TOKEN_KEY).get();
            group.add(channel);
            WSConstants.addChannels(token, ctx.channel());
            //存入redis 以set结构
            redisUtil.hset(sdi_host, token, sdf.format(new Date()));
            redisUtil.hset(netty_redisKey, token, netty_web_host);
            //修改负载数
            redisUtil.hincr(sdi_host,netty_socket_host,1);
            redisUtil.hincr("MAIN_CTL",sdi_host,1);
            loger.info("终端：{} 上线。。channelId:{}", token, channel.id().asLongText());

            //发送远程指令集
            WSConstants.executor.execute(new Runnable() {
                @Override
                public void run() {
                    TRemotecmdInfo tRemotecmdInfo = tRemotecmdInfoService.queryByDeviceId(token);
                    //需要推送
                    Message message = new Message(tRemotecmdInfo, MessageType.REQUEST, UsageMessageType.REMOTE_CMDS);
                    try {
                        if (channel != null) {
                            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message, SerializerFeature.DisableCircularReferenceDetect)));
                            WSConstants.sentMsgs.putIfAbsent(message.getMsgId(), message);
                            loger.info("远程指令集下发成功}{}--->{}", token, message);
                        } else {
                            loger.error("远程指令集下发失败 未找到终端:{}", sdi_host, token);
                        }
                    } catch (Exception ex) {
                        loger.error("远程指令集下发失败：Target{};message:{};detail:{}", token, tRemotecmdInfo, ex.getMessage());
                    }
                }
            });
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String token = ctx.channel().attr(WSConstants.CHANNEL_TOKEN_KEY).get();
        String text = msg.text();
        JSONObject receive = JSONObject.parseObject(text);
        String msgId = receive.getString("msgId");
        String messageType = receive.getString("messageType");
        String usageType = receive.getString("usageType");
        String message = receive.getString("message");
        if (messageType.equals(MessageType.REQUEST.getMessageType())){
            //数据上报
            WSConstants.executor.execute(new Runnable() {
                @Override
                public void run() {
                    if (UsageMessageType.REPORT_GPS.getUsageType().equals(usageType)){
                        //gps位置上报
                        TGpsHisExtra tGpsHisExtra = JSONObject.parseObject(message, TGpsHisExtra.class);
                        tGpsHisService.insert(tGpsHisExtra);
                        //更新redis中设备的位置信息
                        redisUtil.hset(STAInfo_redisKey,token,tGpsHisExtra);

                    }else if (UsageMessageType.REPORT_FLUX.getUsageType().equals(usageType)){
                        //流量上报
                        TDeviceFlux tDeviceFlux = JSONObject.parseObject(message, TDeviceFlux.class);
                        tDeviceFluxService.insert(tDeviceFlux);
                    }
                    //响应处理结果
                    Map resp = new HashMap<>();
                    resp.put("code","200");
                    Message message = new Message(resp, MessageType.RESPONSE, null);
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message, SerializerFeature.DisableCircularReferenceDetect)));
                }
            });
        }else{
            //终端响应数据
            Message remove = WSConstants.sentMsgs.remove(msgId);
            if (remove != null) {
                JSONObject jsonObject = JSONObject.parseObject(message);
                String code = jsonObject.getString("code");
                if (code.equals("200")) {
                    //终端设备接收消息成功
                    loger.info("终端设备{} 接收消息成功", token);
                } else loger.error("终端设备{} 处理消息失败:{}", token, jsonObject.getString("message"));
            }
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
        WSConstants.removeChannels(channel, redisUtil, sdi_host,netty_socket_host,netty_redisKey, STAInfo_redisKey);
        group.remove(channel);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        loger.error("TextWebSocketFrameHandler exceptionCaught Throwable cause.getMessage()={}", cause.getMessage());
        offlines(ctx);
    }
}
