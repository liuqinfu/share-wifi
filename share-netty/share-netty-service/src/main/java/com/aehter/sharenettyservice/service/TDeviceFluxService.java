package com.aehter.sharenettyservice.service;

import com.aehter.sharenettyservice.entity.TDeviceFlux;
import java.util.List;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)表服务接口
 *
 * @author 我走路带风
 * @since 2020-08-19 09:56:34
 */
public interface TDeviceFluxService {


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
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

    /**
     * 修改数据
     *
     * @param tDeviceFlux 实例对象
     * @return 实例对象
     */
    TDeviceFlux update(TDeviceFlux tDeviceFlux);


}