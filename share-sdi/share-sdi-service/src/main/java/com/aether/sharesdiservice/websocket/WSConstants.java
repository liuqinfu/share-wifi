package com.aether.sharesdiservice.websocket;

import com.aether.sharecommon.utils.RedisUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class WSConstants {
    public static final AttributeKey<String> CHANNEL_TOKEN_KEY = AttributeKey.valueOf("netty.channel.token");  
	/**用来保存当前在线人员*/
	public static Map<String, ChannelId> tokenchannelsId = new ConcurrentHashMap<>();
	public static Map<ChannelId,String> channelsIdtoken = new ConcurrentHashMap<>();
	public static Map<ChannelId, Channel> onlineChannels = new ConcurrentHashMap<>();
	public static Map<String, Message> sentMsgs = new ConcurrentHashMap<>();

	public static void addChannels(String token, Channel channel) {
		tokenchannelsId.put(token, channel.id());
		channelsIdtoken.put(channel.id(),token);
		onlineChannels.put(channel.id(),channel);
	}

	//移除断线用户，由于有超时情况，需要判断用户是否重新连接
	public static void removeChannels(Channel channel, RedisUtil redisUtil, String sdi_host, String STAInfo_redisKey) {
		String token = channelsIdtoken.get(channel.id());
		if(StringUtils.isNotEmpty(token) && tokenchannelsId.containsKey(token)){
			//如果终端在读超时之前已经重新建立连接，那么不能删除tokenchannelsId
			if (tokenchannelsId.get(token).asLongText().equals(channel.id().asLongText())){
				tokenchannelsId.remove(token);
				try {
					if (StringUtils.isNotBlank(token)){
						redisUtil.hdel(sdi_host,token);//解除sdi与终端的关系
						redisUtil.hdel(STAInfo_redisKey,token);
						log.warn("终端：{} 下线。channelId:{}",token,channel.id().asLongText());
					}
				} catch (Exception e) {
					log.error("WSConstants removeChannels Exception e={}", e);
				}
			}
			channelsIdtoken.remove(channel.id());
		}
		onlineChannels.remove(channel.id());
	}
	public static Channel getChannel(String token){
		if(StringUtils.isNotEmpty(token) && tokenchannelsId.containsKey(token)){
			return onlineChannels.get(tokenchannelsId.get(token));
		}
		return null;
	}
}
