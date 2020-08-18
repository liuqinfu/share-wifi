package com.aether.sharesdiservice.service.impl;

import com.aether.sharesdiservice.entity.TRemotecmdInfo;
import com.aether.sharesdiservice.dao.TRemotecmdInfoDao;
import com.aether.sharesdiservice.service.TRemotecmdInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TRemotecmdInfo)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-18 17:47:23
 */
@Service("tRemotecmdInfoService")
public class TRemotecmdInfoServiceImpl implements TRemotecmdInfoService {
    @Resource
    private TRemotecmdInfoDao tRemotecmdInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TRemotecmdInfo queryById(Integer id) {
        return this.tRemotecmdInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TRemotecmdInfo> queryAllByLimit(int offset, int limit) {
        return this.tRemotecmdInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tRemotecmdInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TRemotecmdInfo insert(TRemotecmdInfo tRemotecmdInfo) {
        this.tRemotecmdInfoDao.insert(tRemotecmdInfo);
        return tRemotecmdInfo;
    }

    /**
     * 修改数据
     *
     * @param tRemotecmdInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TRemotecmdInfo update(TRemotecmdInfo tRemotecmdInfo) {
        this.tRemotecmdInfoDao.update(tRemotecmdInfo);
        return this.queryById(tRemotecmdInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tRemotecmdInfoDao.deleteById(id) > 0;
    }
}