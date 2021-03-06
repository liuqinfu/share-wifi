package com.aether.sharemainctlservice.service;

import com.aether.sharemainctlservice.entity.TFluxMeal;
import java.util.List;
import java.util.Map;

/**
 * (TFluxMeal)表服务接口
 *
 * @author 我走路带风
 * @since 2020-08-20 17:32:29
 */
public interface TFluxMealService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TFluxMeal queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TFluxMeal> queryAllByLimit(int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param param 过滤条件参数
     * @return 对象列表
     */
    List<TFluxMeal> queryAll(Map param);

    /**
     * 新增数据
     *
     * @param tFluxMeal 实例对象
     * @return 实例对象
     */
    TFluxMeal insert(TFluxMeal tFluxMeal);

    /**
     * 修改数据
     *
     * @param tFluxMeal 实例对象
     * @return 实例对象
     */
    TFluxMeal update(TFluxMeal tFluxMeal);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}