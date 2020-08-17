package com.aehter.sharenettyservice;

import com.aehter.sharenettyservice.websocket.WSServer;
import com.aether.sharecommon.utils.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
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
import java.util.Map;

/**
 * @author 我走路带风
 * @since 2020/8/17 13:59
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
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
    public void PostConstruct(){
        //注册netty服务
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("ip",socket_host);
        jsonObject.put("port",socket_port);
        String service_url = socket_host+":"+socket_port;
        boolean regResult = createTmpNode("/192.168.11.110/"+service_url, jsonObject.toJSONString());
        if (!regResult){
            //netty注册失败，停止服务
            exitApplication(ctx);
        }


    }

    @Override
    public void run(String... args) throws Exception {
        ChannelFuture future = WSServer.start(socket_port, null);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                WSServer.destroy();
                //注销netty
            }
        });
        future.channel().closeFuture().syncUninterruptibly();

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
