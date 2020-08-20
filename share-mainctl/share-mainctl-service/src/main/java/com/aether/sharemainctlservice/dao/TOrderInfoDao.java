package com.aether.sharemainctlservice.dao;

import com.aether.sharemainctlservice.entity.TOrderInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TOrderInfo)表数据库访问层
 *
 * @author 我走路带风
 * @since 2020-08-20 17:33:50
 */
public interface TOrderInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TOrderInfo queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TOrderInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tOrderInfo 实例对象
     * @return 对象列表
     */
    List<TOrderInfo> queryAll(TOrderInfo tOrderInfo);

    /**
     * 新增数据
     *
     * @param tOrderInfo 实例对象
     * @return 影响行数
     */
    int insert(TOrderInfo tOrderInfo);

    /**
     * 修改数据
     *
     * @param tOrderInfo 实例对象
     * @return 影响行数
     */
    int update(TOrderInfo tOrderInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}