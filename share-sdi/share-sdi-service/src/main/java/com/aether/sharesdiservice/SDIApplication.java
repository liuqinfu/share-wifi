package com.aether.sharesdiservice;

import com.aether.sharecommon.utils.RedisUtil;
import io.netty.channel.ChannelFuture;
import lombok.SneakyThrows;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

/**
 * @author 我走路带风
 * @since 2020/8/12 13:40
 */
//@SpringBootApplication(exclude = {RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = {"com.aether.sharesdiservice.dao"})
public class SDIApplication implements CommandLineRunner {

    @Autowired
    private RedisUtil redisUtil;

    @Value("${server.host}")
    private String ip;

    @Value("${server.port}")
    private int port;

    @Autowired
    private ZooKeeper zkClient;

    public static void main(String[] args) {
        SpringApplication.run(SDIApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                Stat exists = zkClient.exists("/LB/192.168.10.226", false);
                if (exists != null){
                    delNode("/LB/192.168.10.226");
                }
                String serverAddr = ip+":"+port;
                redisUtil.hdel("MAIN_CTL", serverAddr);
            }
        });


    }

    public void  delNode(String basePath) throws Exception {
        zkClient.removeAllWatches(basePath, Watcher.WatcherType.Any,true);
        delPath(basePath);
        zkClient.delete(basePath, -1);

    }

    public void delPath(String basePath) throws Exception{
        List<String> paths=zkClient.getChildren(basePath, false);
        if (paths.size() == 0){
            return;
        }
        for (String p:paths){
            delPath(basePath+"/"+p);
            System.out.println(basePath+"/"+p);
            zkClient.delete(basePath+"/"+p, -1);
        }
    }
}
