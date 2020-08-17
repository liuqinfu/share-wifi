package com.aether.sharesdiservice.config;

import com.aether.sharesdiservice.listener.ZooListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.watch.WatcherMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

/**
 * @author 我走路带风
 * @since 2020/8/17 14:27
 */
@Slf4j
@Configuration
public class ZookeeperConfig {

    @Value("${zookeeper.address}")
    private    String connectString;

    @Value("${zookeeper.timeout}")
    private  int timeout;

    @Autowired
    private ZooListener zooListener;


    @Bean(name = "zkClient")
    public ZooKeeper zkClient(){
        ZooKeeper zooKeeper=null;
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            //连接成功后，会回调watcher监听，此连接操作是异步的，执行完new语句后，直接调用后续代码
            //  可指定多台服务地址 127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183
            zooKeeper = new ZooKeeper(connectString, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    // 获取事件状态
                    Event.KeeperState keeperState = watchedEvent.getState();
                    // 获取事件类型
                    Event.EventType eventType = watchedEvent.getType();
                    // zk 路径
                    String path = watchedEvent.getPath();
                    if (Event.KeeperState.SyncConnected == keeperState) {
                        //如果收到了服务端的响应事件,连接成功
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            log.info("【初始化ZooKeeper连接状态....】={}",zooKeeper.getState());
            Stat exists = zooKeeper.exists("/192.168.11.110", false);
            if (exists == null){
                zooKeeper.create("/192.168.11.110",null, ZooDefs.Ids.OPEN_ACL_UNSAFE ,CreateMode.PERSISTENT);
            }
            zooKeeper.addWatch("/192.168.11.110",zooListener.getWatcher(), AddWatchMode.PERSISTENT);

        }catch (Exception e){
            log.error("初始化ZooKeeper连接异常....】={}",e);
            System.exit(0);
        }
        return  zooKeeper;
    }
}
