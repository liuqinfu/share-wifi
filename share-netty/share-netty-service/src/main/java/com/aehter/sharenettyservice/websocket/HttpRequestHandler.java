package com.aehter.sharenettyservice.websocket;

import com.aehter.sharenettyservice.entity.TRemotecmdInfo;
import com.aehter.sharenettyservice.service.TRemotecmdInfoService;
import com.aether.sharecommon.utils.RedisUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestHandler.class);

    private final String webUri;
    private RedisUtil redisUtil;
    private String sdi_host;
    private String netty_web_host;
    private String netty_socket_host;
    private String netty_redisKey;
    private String STAInfo_redisKey;


    public HttpRequestHandler(String webUri,
                              RedisUtil redisUtil,
                              String sdi_host,
                              String netty_web_host,
                              String netty_socket_host,
                              String netty_redisKey,
                              String STAInfo_redisKey) {
        this.webUri = webUri;
        this.redisUtil = redisUtil;
        this.sdi_host = sdi_host;
        this.netty_web_host = netty_web_host;
        this.netty_socket_host = netty_socket_host;
        this.netty_redisKey = netty_redisKey;
        this.STAInfo_redisKey = STAInfo_redisKey;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) {
        LOGGER.info("HttpRequestHandler channelRead0 ===========> {}, {}", webUri, request.uri());

        String uri = StringUtils.substringBefore(request.uri(), "?");
        if (webUri.equalsIgnoreCase(uri)) {
            //获取webSocket参数
            QueryStringDecoder query = new QueryStringDecoder(request.uri());
            Map<String, List<String>> map = query.parameters();
            List<String> tokens = map.get("token");
            List<String> bssidSsid = map.get("bssid_ssid");
            //根据参数保存当前登录对象, 并把该token加入到channel中
            if (tokens != null && !tokens.isEmpty() ) {
                String token = tokens.get(0);
                ctx.channel().attr(WSConstants.CHANNEL_TOKEN_KEY).getAndSet(token);
                if (bssidSsid != null && !bssidSsid.isEmpty())ctx.channel().attr(WSConstants.CHANNEL_AP_KEY).getAndSet(bssidSsid.get(0));
            }else{
                ctx.close();
            }
            request.setUri(uri);
            ctx.fireChannelRead(request.retain());
        }else{
            LOGGER.info("httprequest else");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("HttpRequestHandler exceptionCaught cause.getMessage={}", cause.getMessage());
        try {
            String token = ctx.channel().attr(WSConstants.CHANNEL_TOKEN_KEY).get();
            if (StringUtils.isNotEmpty(token)) {
                redisUtil.hdel(sdi_host, token);//解除sdi与终端的关系
                redisUtil.hdel(STAInfo_redisKey,token);
                redisUtil.hdel(netty_redisKey,token);
                //修改负载数
                redisUtil.hdecr(sdi_host,netty_socket_host,1);
                redisUtil.hdecr("MAIN_CTL",sdi_host,1);

                LOGGER.warn("终端：{} 下线。", token);
            }
        } catch (Exception e) {
            LOGGER.error("HttpRequestHandler exceptionCaught e.getMessage={}", e.getMessage());
        }
        ctx.close();
    }
}
