package com.aether.sharemainctlservice.dao;

import com.aether.sharemainctlservice.entity.TWifiEvent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 终端设备连接与断开热点的事件记录(TWifiEvent)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-16 13:26:19
 */
public interface TWifiEventDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TWifiEvent queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TWifiEvent> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tWifiEvent 实例对象
     * @return 对象列表
     */
    List<TWifiEvent> queryAll(TWifiEvent tWifiEvent);

    /**
     * 新增数据
     *
     * @param tWifiEvent 实例对象
     * @return 影响行数
     */
    int insert(TWifiEvent tWifiEvent);

    /**
     * 修改数据
     *
     * @param tWifiEvent 实例对象
     * @return 影响行数
     */
    int update(TWifiEvent tWifiEvent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}