package com.aether.sharemainctlservice.dao;

import com.aether.sharemainctlservice.entity.TWifiInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 热点信息(TWifiInfo)表数据库访问层
 *
 * @author 我走路带风
 * @since 2020-08-14 17:05:57
 */
public interface TWifiInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param deviceId 主键
     * @return 实例对象
     */
    TWifiInfo queryById(String deviceId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TWifiInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tWifiInfo 实例对象
     * @return 对象列表
     */
    List<TWifiInfo> queryAll(TWifiInfo tWifiInfo);

    /**
     * 新增数据
     *
     * @param tWifiInfo 实例对象
     * @return 影响行数
     */
    int insert(TWifiInfo tWifiInfo);

    /**
     * 修改数据
     *
     * @param tWifiInfo 实例对象
     * @return 影响行数
     */
    int update(TWifiInfo tWifiInfo);

    /**
     * 通过主键删除数据
     *
     * @param deviceId 主键
     * @return 影响行数
     */
    int deleteById(String deviceId);

}