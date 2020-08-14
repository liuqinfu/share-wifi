package com.aether.sharemainctlservice.service;

import com.aether.sharemainctlservice.entity.TWifiInfo;
import java.util.List;

/**
 * 热点信息(TWifiInfo)表服务接口
 *
 * @author 我走路带风
 * @since 2020-08-14 17:05:57
 */
public interface TWifiInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param deviceId 主键
     * @return 实例对象
     */
    TWifiInfo queryById(String deviceId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TWifiInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tWifiInfo 实例对象
     * @return 实例对象
     */
    TWifiInfo insert(TWifiInfo tWifiInfo);

    /**
     * 修改数据
     *
     * @param tWifiInfo 实例对象
     * @return 实例对象
     */
    TWifiInfo update(TWifiInfo tWifiInfo);

    /**
     * 通过主键删除数据
     *
     * @param deviceId 主键
     * @return 是否成功
     */
    boolean deleteById(String deviceId);

}