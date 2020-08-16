package com.aether.sharesdiservice.websocket;

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

	/**
	 * 本服务ip
	 */
	@Value("${server.host}")
	private String serverHost;

	/**
	 * STA终端信息
	 */
	@Value("${redis.keys.sta}")
	private String STAInfo_redisKey;

	/**
	 * web服务端口
	 */
	@Value("${server.port}")
	private Integer serverport;

	public ChannelFuture start(int port, SslContext sslContext) {
		String sdi_host=serverHost+":"+serverport;
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new WSServerInitializer(channelGroup,sslContext,redisUtil,tRemotecmdInfoService,sdi_host,STAInfo_redisKey))
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
	
	/*public static void main(String[] args) {
		ChatServer server = new ChatServer();
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9090);
		ChannelFuture future = server.start(address);
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				server.destroy();
			}
		});
		
		future.channel().closeFuture().syncUninterruptibly();
	}*/
}
