package com.liucan.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liucan
 * @date 2018/7/22
 * @brief
 */
@Component
public class Zookeepers {
    void example() {
        ZooKeeper zk;
        try {
            //禁用sasl认证，否则会报错
            System.setProperty("zookeeper.sasl.client", "false");
            // 创建一个与服务器的连接
            zk = new ZooKeeper("192.168.32.128:2181", 15000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    // 监控所有被触发的事
                    if (event.getType() == Event.EventType.NodeDataChanged) {
                        System.out.println("已经触发了" + event.getType() + "事件" + "path:" + event.getPath());
                    }
                }
            });

            Stat stat = zk.exists("/zookeeper/liucan", true);
            if (stat == null) {
                // 创建一个目录节点
                zk.create("/zookeeper/liucan", "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            zk.setData("/zookeeper/liucan", "data1122".getBytes(), -1);
            // 创建一个子目录节点
            zk.create("/zookeeper/liucan/app1", "data1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            zk.create("/zookeeper/liucan/app2", "data2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            // 取出子目录节点列表
            List<String> list = zk.getChildren("/zookeeper/liucan", false);

            String data = new String(zk.getData("/zookeeper/liucan", false, null));

            //关闭连接
            zk.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
