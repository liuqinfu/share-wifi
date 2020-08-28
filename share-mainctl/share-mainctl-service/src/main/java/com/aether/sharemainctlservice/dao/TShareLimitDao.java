package com.aether.sharemainctlservice.dao;

import com.aether.sharemainctlservice.entity.TShareLimit;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TShareLimit)表数据库访问层
 *
 * @author 我走路带风
 * @since 2020-08-28 16:30:53
 */
public interface TShareLimitDao {

    /**
     * 通过ID查询单条数据
     *
     * @param deviceId 主键
     * @return 实例对象
     */
    TShareLimit queryById(String deviceId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TShareLimit> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tShareLimit 实例对象
     * @return 对象列表
     */
    List<TShareLimit> queryAll(TShareLimit tShareLimit);

    /**
     * 新增数据
     *
     * @param tShareLimit 实例对象
     * @return 影响行数
     */
    int insert(TShareLimit tShareLimit);

    /**
     * 修改数据
     *
     * @param tShareLimit 实例对象
     * @return 影响行数
     */
    int update(TShareLimit tShareLimit);

    /**
     * 通过主键删除数据
     *
     * @param deviceId 主键
     * @return 影响行数
     */
    int deleteById(String deviceId);

}