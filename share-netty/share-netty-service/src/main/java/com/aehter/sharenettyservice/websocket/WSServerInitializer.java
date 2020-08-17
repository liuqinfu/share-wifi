package com.aehter.sharenettyservice.websocket;

import com.aether.sharecommon.utils.RedisUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class WSServerInitializer extends ChannelInitializer<Channel> {
    private final ChannelGroup group;
    private final SslContext sslContext;

    private RedisUtil redisUtil;
    private String sdi_host;
    private String STAInfo_redisKey;

    public WSServerInitializer(ChannelGroup group, SslContext sslContext, RedisUtil redisUtil, String sdi_host, String STAInfo_redisKey) {
        this.group = group;
        this.sslContext = sslContext;
        this.redisUtil = redisUtil;
        this.sdi_host = sdi_host;
        this.STAInfo_redisKey = STAInfo_redisKey;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (sslContext != null) pipeline.addLast(sslContext.newHandler(ch.alloc()));
        //处理日志
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
//        pipeline.addLast(new UserHandler());

        //处理心跳
        pipeline.addLast(new IdleStateHandler(4, 2, 0, TimeUnit.SECONDS));
        pipeline.addLast(new HeartbeatHandler());

//        pipeline.addLast(new ReadTimeoutHandler(4,TimeUnit.SECONDS));

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new HttpRequestHandler("/ws", redisUtil, sdi_host,STAInfo_redisKey));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler(group, redisUtil, sdi_host,STAInfo_redisKey));
    }
}
