package com.aether.sharesdiservice.dao;

import com.aether.sharesdiservice.entity.TDeviceFlux;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-16 12:37:17
 */
public interface TDeviceFluxDao {

    /**
     * 通过ID查询单条数据
     *
     * @param params 主键
     * @return 实例对象
     */
    List<Map> countByParam(Map params);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TDeviceFlux> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDeviceFlux 实例对象
     * @return 对象列表
     */
    List<TDeviceFlux> queryAll(TDeviceFlux tDeviceFlux);

    /**
     * 新增数据
     *
     * @param tDeviceFlux 实例对象
     * @return 影响行数
     */
    int insert(TDeviceFlux tDeviceFlux);

    /**
     * 修改数据
     *
     * @param tDeviceFlux 实例对象
     * @return 影响行数
     */
    int update(TDeviceFlux tDeviceFlux);

}