package com.aether.sharemainctlservice.service;

import com.aether.sharemainctlservice.entity.TDeviceFlux;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)表服务接口
 *
 * @author 我走路带风
 * @since 2020-08-20 10:46:08
 */
public interface TDeviceFluxService {

    /**
     * 统计sta使用的流量
     *
     * @param params 参数
     * @return 实例对象
     */
    List<Map> countSTAByParam(Map params);

    /**
     * 按条件查询流量使用明细
     * @param params
     * @return
     */
    List<Map> queryDetailByParam(Map params);

    /**
     * 统计AP共享的流量
     *
     * @param params 参数
     * @return 实例对象
     */
    List<Map> countAPByParam(Map params);

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