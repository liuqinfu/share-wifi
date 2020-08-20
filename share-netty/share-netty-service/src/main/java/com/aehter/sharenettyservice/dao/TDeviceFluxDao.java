package com.aehter.sharenettyservice.dao;

import com.aehter.sharenettyservice.entity.TDeviceFlux;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)表数据库访问层
 *
 * @author 我走路带风
 * @since 2020-08-19 09:56:34
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