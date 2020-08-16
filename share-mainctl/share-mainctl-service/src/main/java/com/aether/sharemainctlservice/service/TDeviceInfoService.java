package com.aether.sharemainctlservice.service;

import com.aether.sharemainctlservice.entity.TDeviceInfo;
import com.aether.sharemainctlservice.entity.TGpsHis;

import java.util.List;

/**
 * 终端设备信息(TDeviceInfo)表服务接口
 *
 * @author 我走路带风
 * @since 2020-08-14 13:22:18
 */
public interface TDeviceInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param deviceId 主键
     * @return 实例对象
     */
    TDeviceInfo queryById(String deviceId);

    /**
     * 查询所有设备在线离线状态
     * @return
     */
    List<TDeviceInfo> queryDevicesOnlinesOr();

    /**
     * 登陆或注册
     *
     * @param tDeviceInfo 终端设备信息
     * @param tGpsHis 终端设备GPS
     * @return 实例对象
     */
    TDeviceInfo regLogin(TDeviceInfo tDeviceInfo, TGpsHis tGpsHis);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDeviceInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tDeviceInfo 实例对象
     * @return 实例对象
     */
    TDeviceInfo insert(TDeviceInfo tDeviceInfo);

    /**
     * 修改数据
     *
     * @param tDeviceInfo 实例对象
     * @return 实例对象
     */
    TDeviceInfo update(TDeviceInfo tDeviceInfo);

    /**
     * 通过主键删除数据
     *
     * @param deviceId 主键
     * @return 是否成功
     */
    boolean deleteById(String deviceId);

}