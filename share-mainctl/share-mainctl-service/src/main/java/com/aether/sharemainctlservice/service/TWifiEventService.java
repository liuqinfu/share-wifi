package com.aether.sharemainctlservice.service;

import com.aether.sharemainctlservice.entity.TWifiEvent;

import java.util.List;

/**
 * 终端设备连接与断开热点的事件记录(TWifiEvent)表服务接口
 *
 * @author makejava
 * @since 2020-08-16 13:26:20
 */
public interface TWifiEventService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TWifiEvent queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TWifiEvent> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tWifiEvent 实例对象
     * @return 实例对象
     */
    TWifiEvent insert(TWifiEvent tWifiEvent);

    /**
     * 修改数据
     *
     * @param tWifiEvent 实例对象
     * @return 实例对象
     */
    TWifiEvent update(TWifiEvent tWifiEvent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}