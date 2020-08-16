package com.aether.sharesdiservice.service;

import com.aether.sharesdiservice.entity.TDeviceFlux;

import java.util.List;
import java.util.Map;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)表服务接口
 *
 * @author makejava
 * @since 2020-08-16 12:37:22
 */
public interface TDeviceFluxService {

    /**
     * 通过ID查询单条数据
     *
     * @param params 参数
     * @return 实例对象
     */
    List<Map> countByParam(Map params);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TDeviceFlux> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tDeviceFlux 实例对象
     * @return 实例对象
     */
    TDeviceFlux insert(TDeviceFlux tDeviceFlux);


}