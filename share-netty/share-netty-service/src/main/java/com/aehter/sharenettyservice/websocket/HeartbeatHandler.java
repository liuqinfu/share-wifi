package com.aehter.sharenettyservice.websocket;

import com.aehter.sharenettyservice.enums.MessageType;
import com.aehter.sharenettyservice.enums.UsageMessageType;
import com.aehter.sharenettyservice.websocket.module.Message;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartbeatHandler extends ChannelInboundHandlerAdapter {
    //	private Logger logger = LogManager.getLogger();
    private Logger logger = LoggerFactory.getLogger(TextWebSocketFrameHandler.class);
    public static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("HB", CharsetUtil.UTF_8));

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if ( state == IdleState.WRITER_IDLE) {
                System.out.println("服务端发送心跳");
                Message message = new Message("HB", MessageType.REQUEST, UsageMessageType.HEART_BREAK);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message, SerializerFeature.DisableCircularReferenceDetect)));
            }else if (state == IdleState.READER_IDLE){
                ctx.close();
            }else {
                super.userEventTriggered(ctx, evt);
            }
        }
        /*if (evt instanceof IdleStateEvent) {
            logger.info("====>Heartbeat: greater than {}", 2);
//            ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());//.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            ctx.channel().writeAndFlush(new TextWebSocketFrame("qwewqewqewqe"));
        } else {
            super.userEventTriggered(ctx, evt);
        }*/
    }
}
