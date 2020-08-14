package com.aether.sharemainctlservice.service;

import com.aether.sharemainctlservice.entity.TGpsHis;
import java.util.List;

/**
 * 终端设备位置表(TGpsHis)表服务接口
 *
 * @author 我走路带风
 * @since 2020-08-14 17:00:28
 */
public interface TGpsHisService {

    /**
     * 通过ID查询单条数据
     *
     * @param  主键
     * @return 实例对象
     */
    TGpsHis queryById( );

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TGpsHis> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tGpsHis 实例对象
     * @return 实例对象
     */
    TGpsHis insert(TGpsHis tGpsHis);

    /**
     * 修改数据
     *
     * @param tGpsHis 实例对象
     * @return 实例对象
     */
    TGpsHis update(TGpsHis tGpsHis);

    /**
     * 通过主键删除数据
     *
     * @param  主键
     * @return 是否成功
     */
    boolean deleteById( );

}