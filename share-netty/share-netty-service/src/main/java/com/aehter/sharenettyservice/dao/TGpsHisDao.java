package com.aehter.sharenettyservice.dao;

import com.aehter.sharenettyservice.entity.TGpsHis;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 终端设备位置表(TGpsHis)表数据库访问层
 *
 * @author 我走路带风
 * @since 2020-08-19 09:55:05
 */
public interface TGpsHisDao {


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TGpsHis> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tGpsHis 实例对象
     * @return 对象列表
     */
    List<TGpsHis> queryAll(TGpsHis tGpsHis);

    /**
     * 新增数据
     *
     * @param tGpsHis 实例对象
     * @return 影响行数
     */
    int insert(TGpsHis tGpsHis);

    /**
     * 修改数据
     *
     * @param tGpsHis 实例对象
     * @return 影响行数
     */
    int update(TGpsHis tGpsHis);


}