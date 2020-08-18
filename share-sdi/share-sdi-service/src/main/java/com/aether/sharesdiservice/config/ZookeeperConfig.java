package com.aether.sharesdiservice.config;

import com.aether.sharecommon.utils.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 我走路带风
 * @since 2020/8/17 14:27
 */
@Slf4j
@Configuration
public class ZookeeperConfig {

    @Value("${zookeeper.address}")
    private String connectString;

    @Value("${zookeeper.timeout}")
    private int timeout;

    @Value("${server.host}")
    private String ip;

    @Value("${server.port}")
    private int port;

    @Autowired
    private RedisUtil redisUtil;

    @Bean(name = "zkClient")
    public ZooKeeper zkClient() {
        ZooKeeper zooKeeper = null;
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
            log.info("【初始化ZooKeeper连接状态....】={}", zooKeeper.getState());
//            先监听，在创建“/LB/192.168.11.110”节点时，触发节点创建事件
            zooKeeper.addWatch("/LB/192.168.11.110", new MyWatcher(zooKeeper,redisUtil,ip,port), AddWatchMode.PERSISTENT);
            Stat exists = zooKeeper.exists("/LB/192.168.11.110", false);
            if (exists == null) {
                Stat exists2 = zooKeeper.exists("/LB", false);
                if (exists2 == null )zooKeeper.create("/LB",null, ZooDefs.Ids.OPEN_ACL_UNSAFE ,CreateMode.PERSISTENT);
                zooKeeper.create("/LB/192.168.11.110", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            log.error("初始化ZooKeeper连接异常....】={}", e);
            System.exit(0);
        }
        return zooKeeper;
    }

    /*Watcher watcher = new Watcher() {
        @SneakyThrows
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
                } else if (Watcher.Event.EventType.NodeDataChanged == eventType) {
                    log.info("事件通知,当前node节点" + path + "被修改....");
                } else if (Watcher.Event.EventType.NodeDeleted == eventType) {
                    log.info("事件通知,当前node节点" + path + "被删除....");
                } else if (Watcher.Event.EventType.NodeChildrenChanged == eventType) {
                    log.info("事件通知,当前node节点的子节点列表发生改变：" + path);
                    List<String> children = zkClient.getChildren(path, false);
                    log.info(JSONObject.toJSONString(children));
                }
            }
            log.info("--------------------------------------------------------");
        }
    };*/
}

@Slf4j
class MyWatcher implements Watcher {

    private ZooKeeper zkClient;
    private RedisUtil redisUtil;
    private String ip;
    private int port;
    private String serverAddr;

    public MyWatcher(ZooKeeper zkClient, RedisUtil redisUtil, String ip, int port) {
        this.zkClient = zkClient;
        this.redisUtil = redisUtil;
        this.ip = ip;
        this.port = port;
        serverAddr = this.ip+":"+this.port;
    }

    @SneakyThrows
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
                //SDIbalancer服务启动后初始化SDI集群加载量
                redisUtil.hset("MAIN_CTL",serverAddr,0);
            } else if (Watcher.Event.EventType.NodeDataChanged == eventType) {
                log.info("事件通知,当前node节点" + path + "被修改....");
            } else if (Watcher.Event.EventType.NodeDeleted == eventType) {
                log.info("事件通知,当前node节点" + path + "被删除....");
            } else if (Watcher.Event.EventType.NodeChildrenChanged == eventType) {
                log.info("事件通知,当前node节点的子节点列表发生改变：" + path);
                List<String> children = zkClient.getChildren(path, false);
                log.info(JSONObject.toJSONString(children));
                AtomicInteger loads = new AtomicInteger();
                children.stream().forEach(child->{
                    String childPath = path+"/"+child;
                    byte[] data = new byte[0];
                    try {
                        data = zkClient.getData(childPath, false, null);
                    } catch (Exception e) {
                        log.error("netty节点负载数据加载异常，负载准确度将无法控制");
                    }
                    Integer load = Integer.valueOf(new String(data));
                    redisUtil.hset(this.serverAddr,child,load);
                    System.out.println(this.serverAddr+child+load);
                    loads.addAndGet(load);
                });
                //更新SDIloadbalancer的负载
                redisUtil.hset("MAIN_CTL",this.serverAddr,loads);
            }
        }
        log.info("--------------------------------------------------------");
    }
}
