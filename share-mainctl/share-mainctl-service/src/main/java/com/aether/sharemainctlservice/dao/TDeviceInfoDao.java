package com.aether.sharemainctlservice.dao;

import com.aether.sharemainctlservice.entity.TDeviceInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 终端设备信息(TDeviceInfo)表数据库访问层
 *
 * @author 我走路带风
 * @since 2020-08-14 13:22:16
 */
public interface TDeviceInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param deviceId 主键
     * @return 实例对象
     */
    TDeviceInfo queryById(String deviceId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDeviceInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDeviceInfo 实例对象
     * @return 对象列表
     */
    List<TDeviceInfo> queryAll(TDeviceInfo tDeviceInfo);

    /**
     * 新增数据
     *
     * @param tDeviceInfo 实例对象
     * @return 影响行数
     */
    int insert(TDeviceInfo tDeviceInfo);

    /**
     * 修改数据
     *
     * @param tDeviceInfo 实例对象
     * @return 影响行数
     */
    int update(TDeviceInfo tDeviceInfo);

    /**
     * 通过主键删除数据
     *
     * @param deviceId 主键
     * @return 影响行数
     */
    int deleteById(String deviceId);

}