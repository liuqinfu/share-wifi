package com.aether.xxljob.executor.dao;

import com.aether.xxljob.executor.entity.TDeviceFlux;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)表数据库访问层
 *
 * @author 我走路带风
 * @since 2020-08-24 10:11:01
 */
public interface TDeviceFluxDao {


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDeviceFlux> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    long sumFluxByBeviceIdandTime(@Param("deviceId")String deviceId, @Param("startTime")Date startTime);


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


}