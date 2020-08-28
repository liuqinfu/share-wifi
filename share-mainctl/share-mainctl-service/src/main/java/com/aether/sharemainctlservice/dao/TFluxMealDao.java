package com.aether.sharemainctlservice.dao;

import com.aether.sharemainctlservice.entity.TFluxMeal;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (TFluxMeal)表数据库访问层
 *
 * @author 我走路带风
 * @since 2020-08-20 17:32:29
 */
public interface TFluxMealDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TFluxMeal queryById(String id);

    /**
     * 查询用户的最长有效期套餐
     * @param deviceId 设备唯一标识
     * @return
     */
    TFluxMeal queryLastValidMeal(String deviceId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TFluxMeal> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询多条数据
     *
     * @param param 过滤条件参数
     * @return 对象列表
     */
    List<TFluxMeal> queryAll(Map param);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tFluxMeal 实例对象
     * @return 对象列表
     */
    List<TFluxMeal> queryAll(TFluxMeal tFluxMeal);

    /**
     * 新增数据
     *
     * @param tFluxMeal 实例对象
     * @return 影响行数
     */
    int insert(TFluxMeal tFluxMeal);

    /**
     * 修改数据
     *
     * @param tFluxMeal 实例对象
     * @return 影响行数
     */
    int update(TFluxMeal tFluxMeal);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}