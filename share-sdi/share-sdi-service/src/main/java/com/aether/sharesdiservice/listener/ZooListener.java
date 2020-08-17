package com.aether.sharesdiservice.listener;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author 我走路带风
 * @since 2020/8/17 14:52
 */
@Configuration
public class ZooListener{

    Watcher watcher = new Watcher(){
        @Override
        public void process(WatchedEvent watchedEvent) {
            // 获取事件状态
            Watcher.Event.KeeperState keeperState = watchedEvent.getState();
            // 获取事件类型
            Watcher.Event.EventType eventType = watchedEvent.getType();
            // zk 路径
            String path = watchedEvent.getPath();
            System.out.println("进入到 process() keeperState:" + keeperState + ", eventType:" + eventType + ", path:" + path);
            // 判断是否建立连接
            if (Watcher.Event.KeeperState.SyncConnected == keeperState) {
                if (Watcher.Event.EventType.NodeCreated == eventType) {
                    System.out.println("事件通知,新增node节点" + path);
                } else if (Watcher.Event.EventType.NodeDataChanged == eventType) {
                    System.out.println("事件通知,当前node节点" + path + "被修改....");
                } else if (Watcher.Event.EventType.NodeDeleted == eventType) {
                    System.out.println("事件通知,当前node节点" + path + "被删除....");
                }else if(Watcher.Event.EventType.NodeChildrenChanged==eventType){
                    System.out.println("事件通知,当前node节点的子节点列表发生改变：" + path );
                }
            }
            System.out.println("--------------------------------------------------------");
        }
    };

    public Watcher getWatcher() {
        return watcher;
    }
}
