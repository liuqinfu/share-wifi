package com.aether.sharemainctlservice.service.impl;

import com.aether.sharemainctlservice.entity.TSetmealInfo;
import com.aether.sharemainctlservice.dao.TSetmealInfoDao;
import com.aether.sharemainctlservice.service.TSetmealInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TSetmealInfo)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-20 17:26:43
 */
@Service("tSetmealInfoService")
public class TSetmealInfoServiceImpl implements TSetmealInfoService {
    @Resource
    private TSetmealInfoDao tSetmealInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TSetmealInfo queryById(String id) {
        return this.tSetmealInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TSetmealInfo> queryAllByLimit(int offset, int limit) {
        return this.tSetmealInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tSetmealInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TSetmealInfo insert(TSetmealInfo tSetmealInfo) {
        this.tSetmealInfoDao.insert(tSetmealInfo);
        return tSetmealInfo;
    }

    /**
     * 修改数据
     *
     * @param tSetmealInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TSetmealInfo update(TSetmealInfo tSetmealInfo) {
        this.tSetmealInfoDao.update(tSetmealInfo);
        return this.queryById(tSetmealInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tSetmealInfoDao.deleteById(id) > 0;
    }
}