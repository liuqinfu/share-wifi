package com.aether.sharesdiservice;

import com.aether.sharecommon.utils.RedisUtil;
import com.aether.sharesdiservice.websocket.Message;
import com.aether.sharesdiservice.websocket.WSConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @NAME: MultithreadScheduleTask
 * @USER: 我走路带风
 * @DATETIME: 2020/5/9 10:02
 * @DESCRIPTION 定时任务，定时获取需要推送的ap信息及接收客户端
 **/
@Slf4j
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync(proxyTargetClass = true)       // 2.开启多线程
public class MultithreadScheduleTask {

    @Autowired
    private RedisUtil redisUtil;

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
     * 切换ap的redis的key
     */
    @Value("${redis.keys.change-ap}")
    private String CHANGE_AP_KEY;

    /**
     * 操作指令的redis的key
     */
    @Value("${redis.keys.operation}")
    private String OPERATION_KEY;

    //线程异步执行
    //    @Async(value = "applicationTaskExecutor")//不需要异步，因为推送过程是有先后顺序的
    @Scheduled(fixedDelay = 1000)  //间隔1秒
    public void getDeviceID() {
        String sdi_host = serverHost + ":" + serverport;
        //拉取需要推送的列表
        Object arr = redisUtil.hget(CHANGE_AP_KEY, sdi_host);
        if (arr == null)return;
        //删除推送
        redisUtil.hdel(CHANGE_AP_KEY, sdi_host);
        List<DeviceAPInfo> deviceAPInfos = JSONObject.parseArray(arr.toString(), DeviceAPInfo.class);
        deviceAPInfos.stream().forEach(e -> {
            //需要推送
            Message message = new Message(e);
            try {
                Channel channel = WSConstants.getChannel(e.getDeviceId());
                if (channel != null){
                    channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message, SerializerFeature.DisableCircularReferenceDetect)));
                    log.info("切换AP指令下发成功:{}--->{}",message,e.getDeviceId());
                }else {
                    log.error("SDI:{} 未找到终端:{}",sdi_host,e.getDeviceId());
                }
            } catch (Exception ex) {
                log.error("切换AP指令下发失败：Target{};AP:{};detail:{}", e.getDeviceId(), e, ex.getMessage());
            }
        });
    }

    //线程异步执行
    //    @Async(value = "applicationTaskExecutor")//不需要异步，因为推送过程是有先后顺序的
    @Scheduled(fixedDelay = 1000)  //间隔1秒
    public void getDeviceID() {
        String sdi_host = serverHost + ":" + serverport;
        //拉取需要推送的列表
        Object arr = redisUtil.hget(OPERATION_KEY, sdi_host);
        if (arr == null)return;
        //删除推送
        redisUtil.hdel(OPERATION_KEY, sdi_host);
        List<DeviceAPInfo> deviceAPInfos = JSONObject.parseArray(arr.toString(), DeviceAPInfo.class);
        deviceAPInfos.stream().forEach(e -> {
            //需要推送
            Message message = new Message(e);
            try {
                Channel channel = WSConstants.getChannel(e.getDeviceId());
                if (channel != null){
                    channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message, SerializerFeature.DisableCircularReferenceDetect)));
                    log.info("远程指令下发成功:{}--->{}",message,e.getDeviceId());
                }else {
                    log.error("SDI:{} 未找到终端:{}",sdi_host,e.getDeviceId());
                }
            } catch (Exception ex) {
                log.error("远程指令下发失败：Target{};AP:{};detail:{}", e.getDeviceId(), e, ex.getMessage());
            }
        });
    }
}
