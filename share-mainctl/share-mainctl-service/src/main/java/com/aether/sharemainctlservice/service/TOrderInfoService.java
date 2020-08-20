package com.aether.sharemainctlservice.service;

import com.aether.sharemainctlservice.entity.TOrderInfo;
import java.util.List;

/**
 * (TOrderInfo)表服务接口
 *
 * @author 我走路带风
 * @since 2020-08-20 17:33:51
 */
public interface TOrderInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TOrderInfo queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TOrderInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tOrderInfo 实例对象
     * @return 实例对象
     */
    TOrderInfo insert(TOrderInfo tOrderInfo);

    /**
     * 修改数据
     *
     * @param tOrderInfo 实例对象
     * @return 实例对象
     */
    TOrderInfo update(TOrderInfo tOrderInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}