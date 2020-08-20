package com.aehter.sharenettyservice.service;

import com.aehter.sharenettyservice.entity.TRemotecmdInfo;
import java.util.List;

/**
 * (TRemotecmdInfo)表服务接口
 *
 * @author 我走路带风
 * @since 2020-08-19 09:57:46
 */
public interface TRemotecmdInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TRemotecmdInfo queryById(Integer id);

    /**
     * 通过设备标识查询单条数据
     *
     * @param deviceId 设备唯一标识
     * @return 实例对象
     */
    TRemotecmdInfo queryByDeviceId(String  deviceId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TRemotecmdInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tRemotecmdInfo 实例对象
     * @return 实例对象
     */
    TRemotecmdInfo insert(TRemotecmdInfo tRemotecmdInfo);

    /**
     * 修改数据
     *
     * @param tRemotecmdInfo 实例对象
     * @return 实例对象
     */
    TRemotecmdInfo update(TRemotecmdInfo tRemotecmdInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}