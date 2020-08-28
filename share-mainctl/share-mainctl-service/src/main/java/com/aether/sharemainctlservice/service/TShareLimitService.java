package com.aether.sharemainctlservice.service;

import com.aether.sharemainctlservice.entity.TShareLimit;
import java.util.List;

/**
 * (TShareLimit)表服务接口
 *
 * @author 我走路带风
 * @since 2020-08-28 16:30:53
 */
public interface TShareLimitService {

    /**
     * 通过ID查询单条数据
     *
     * @param deviceId 主键
     * @return 实例对象
     */
    TShareLimit queryById(String deviceId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TShareLimit> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tShareLimit 实例对象
     * @return 实例对象
     */
    TShareLimit insert(TShareLimit tShareLimit);

    /**
     * 新增数据
     *
     * @param tShareLimit 实例对象
     * @return 实例对象
     */
    TShareLimit save(TShareLimit tShareLimit);

    /**
     * 修改数据
     *
     * @param tShareLimit 实例对象
     * @return 实例对象
     */
    TShareLimit update(TShareLimit tShareLimit);

    /**
     * 通过主键删除数据
     *
     * @param deviceId 主键
     * @return 是否成功
     */
    boolean deleteById(String deviceId);

}