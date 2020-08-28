package com.aehter.sharenettyservice;

import com.aehter.sharenettyservice.websocket.WSConstants;
import com.aehter.sharenettyservice.websocket.WSServer;
import com.aether.sharecommon.utils.HttpUtil;
import com.aether.sharecommon.utils.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 我走路带风
 * @since 2020/8/17 13:59
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.aehter.sharenettyservice.dao"})
public class NettyApplication implements CommandLineRunner {
    private static ConfigurableApplicationContext ctx;
    @Autowired
    private WSServer WSServer;

    /**
     * websocket服务端口
     */
    @Value("${socket.port}")
    private Integer socket_port;

    /**
     * 本服务ip
     */
    @Value("${socket.host}")
    private String socket_host;

    @Autowired
    private ZooKeeper zkClient;


    public static void main(String[] args) {
        ctx = SpringApplication.run(NettyApplication.class, args);
    }


    @PostConstruct
    public void PostConstruct() throws Exception {
        //注册netty服务
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("ip",socket_host);
        jsonObject.put("port",socket_port);
        String service_url = socket_host+":"+socket_port;
        regNetty(service_url,0);
        zkClient.addWatch("/LB/192.168.10.226", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                // 获取事件状态
                Watcher.Event.KeeperState keeperState = watchedEvent.getState();
                // 获取事件类型
                Watcher.Event.EventType eventType = watchedEvent.getType();
                // zk 路径
                String path = watchedEvent.getPath();
                log.info("进入到 process() keeperState:" + keeperState + ", eventType:" + eventType + ", path:" + path);
                // 判断是否建立连接
                if (Watcher.Event.KeeperState.SyncConnected == keeperState) {
                    if (Watcher.Event.EventType.NodeCreated == eventType) {
                        log.info("事件通知,新增node节点" + path);
                        //SDIbalancer服务重新上线后，重新注册netty，并更新负载
                        int load = WSConstants.onlineChannels.size();
                        regNetty(service_url,load);
                    }
                }
                log.info("--------------------------------------------------------");
            }
        }, AddWatchMode.PERSISTENT);


    }

    @Override
    public void run(String... args) throws Exception {
        ChannelFuture future = WSServer.start(socket_port, null);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                WSServer.destroy();
                future.channel().closeFuture().syncUninterruptibly();
            }
        });

    }

    /**
     * 将netty服务注册到SDIloadbalancer中
     * @param service_url
     * @return
     */
    public boolean regNetty(String service_url,int loads){
        boolean regResult = createTmpNode("/LB/192.168.10.226/"+service_url, String.valueOf(loads));
        if (!regResult){
            //netty注册失败，停止服务
            exitApplication(ctx);
        }
        return true;
    }

    /**
     * 创建临时节点
     * @param path
     * @param data
     */
    public boolean createTmpNode(String path, String data){
        try {
            Stat exists = zkClient.exists(path, false);
            if (exists == null)zkClient.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        } catch (Exception e) {
            log.error("【创建持久化节点异常】{},{},{}",path,data,e);
            return false;
        }
    }

    /**
     * 停止服务
     *
     * @param context
     */
    public void exitApplication(ConfigurableApplicationContext context) {
        if (context == null){
            System.exit(0);
        }
        int exitCode = SpringApplication.exit(context, (ExitCodeGenerator) () -> 0);
        System.exit(exitCode);
    }


}
