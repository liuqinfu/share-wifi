package com.aether.sharesdiservice.dao;

import com.aether.sharesdiservice.entity.TRemotecmdInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TRemotecmdInfo)表数据库访问层
 *
 * @author 我走路带风
 * @since 2020-08-18 17:47:23
 */
public interface TRemotecmdInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TRemotecmdInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TRemotecmdInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tRemotecmdInfo 实例对象
     * @return 对象列表
     */
    List<TRemotecmdInfo> queryAll(TRemotecmdInfo tRemotecmdInfo);

    /**
     * 新增数据
     *
     * @param tRemotecmdInfo 实例对象
     * @return 影响行数
     */
    int insert(TRemotecmdInfo tRemotecmdInfo);

    /**
     * 修改数据
     *
     * @param tRemotecmdInfo 实例对象
     * @return 影响行数
     */
    int update(TRemotecmdInfo tRemotecmdInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}