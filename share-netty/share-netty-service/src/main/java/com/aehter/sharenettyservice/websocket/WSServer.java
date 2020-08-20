package com.aehter.sharenettyservice.websocket;

import com.aehter.sharenettyservice.entity.TDeviceFlux;
import com.aehter.sharenettyservice.entity.TGpsHis;
import com.aehter.sharenettyservice.service.TDeviceFluxService;
import com.aehter.sharenettyservice.service.TGpsHisService;
import com.aehter.sharenettyservice.service.TRemotecmdInfoService;
import com.aether.sharecommon.utils.RedisUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.util.concurrent.ImmediateEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WSServer {
	private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup bossGroup = new NioEventLoopGroup();
	private final EventLoopGroup workGroup = new NioEventLoopGroup();
	private Channel channel;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private TRemotecmdInfoService tRemotecmdInfoService;

	@Autowired
	private TDeviceFluxService tDeviceFluxService;

	@Autowired
	private TGpsHisService tGpsHisService;


	/**
	 * 本服务ip
	 */
	@Value("${server.host}")
	private String serverHost;

	/**
	 * web服务端口
	 */
	@Value("${server.port}")
	private Integer serverport;

	/**
	 * socket服务ip
	 */
	@Value("${socket.host}")
	private String socketHost;

	/**
	 * socket服务端口
	 */
	@Value("${socket.port}")
	private Integer socketPort;

	/**
	 * 本netty所属的sdi的ip
	 */
	@Value("${sdi.server.host}")
	private String sditHost;

	/**
	 * 本netty所属的sdi的端口
	 */
	@Value("${sdi.server.port}")
	private Integer sdiport;

	/**
	 * STA终端信息
	 */
	@Value("${redis.keys.sta}")
	private String STAInfo_redisKey;
	/**
	 * 设备与netty的映射信息信息
	 */
	@Value("${redis.keys.netty}")
	private String netty_redisKey;


	public ChannelFuture start(int port, SslContext sslContext) {
		String sdi_host=sditHost+":"+sdiport;
		String netty_web_host=serverHost+":"+serverport;
		String netty_socket_host=socketHost+":"+socketPort;
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new WSServerInitializer(channelGroup,
						sslContext,
						redisUtil,
						tRemotecmdInfoService,
						tDeviceFluxService,
						tGpsHisService,
						sdi_host,
						netty_web_host,
						netty_socket_host,
						netty_redisKey,
						STAInfo_redisKey))
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);

		ChannelFuture future = bootstrap.bind(port).syncUninterruptibly();
		channel = future.channel();
		return future;
	}
	
	public void destroy() {
		if(channel != null) {
			channel.close();
		}
		
		channelGroup.close();
		workGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}
}
