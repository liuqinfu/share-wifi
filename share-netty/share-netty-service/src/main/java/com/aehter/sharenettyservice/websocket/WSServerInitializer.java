package com.aehter.sharenettyservice.websocket;

import com.aehter.sharenettyservice.service.TDeviceFluxService;
import com.aehter.sharenettyservice.service.TGpsHisService;
import com.aehter.sharenettyservice.service.TRemotecmdInfoService;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class WSServerInitializer extends ChannelInitializer<Channel> {
    private final ChannelGroup group;
    private final SslContext sslContext;

    private RedisUtil redisUtil;

    private String sdi_host;
    private String netty_web_host;
    private String netty_socket_host;
    private String netty_redisKey;
    private String STAInfo_redisKey;

    public WSServerInitializer(ChannelGroup group,
                               SslContext sslContext,
                               RedisUtil redisUtil,
                               String sdi_host,
                               String netty_web_host,
                               String netty_socket_host,
                               String netty_redisKey,
                               String STAInfo_redisKey) {
        this.group = group;
        this.sslContext = sslContext;
        this.redisUtil = redisUtil;;
        this.sdi_host = sdi_host;
        this.netty_web_host = netty_web_host;
        this.netty_socket_host = netty_socket_host;
        this.netty_redisKey = netty_redisKey;
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
        pipeline.addLast(new IdleStateHandler(4, 4, 0, TimeUnit.SECONDS));
        pipeline.addLast(new HeartbeatHandler());

//        pipeline.addLast(new ReadTimeoutHandler(4,TimeUnit.SECONDS));

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new HttpRequestHandler("/ws", redisUtil, sdi_host,netty_web_host,netty_socket_host,netty_redisKey,STAInfo_redisKey));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler(group, redisUtil, sdi_host,netty_web_host,netty_socket_host,netty_redisKey,STAInfo_redisKey));
    }
}
