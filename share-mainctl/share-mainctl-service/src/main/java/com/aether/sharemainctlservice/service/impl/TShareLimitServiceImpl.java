package com.aether.sharemainctlservice.service.impl;

import com.aether.sharemainctlservice.entity.TShareLimit;
import com.aether.sharemainctlservice.dao.TShareLimitDao;
import com.aether.sharemainctlservice.service.TShareLimitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (TShareLimit)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-28 16:30:53
 */
@Service("tShareLimitService")
public class TShareLimitServiceImpl implements TShareLimitService {
    @Resource
    private TShareLimitDao tShareLimitDao;

    /**
     * 通过ID查询单条数据
     *
     * @param deviceId 主键
     * @return 实例对象
     */
    @Override
    public TShareLimit queryById(String deviceId) {
        return this.tShareLimitDao.queryById(deviceId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TShareLimit> queryAllByLimit(int offset, int limit) {
        return this.tShareLimitDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tShareLimit 实例对象
     * @return 实例对象
     */
    @Override
    public TShareLimit insert(TShareLimit tShareLimit) {
        this.tShareLimitDao.insert(tShareLimit);
        return tShareLimit;
    }

    /**
     * 新增数据
     *
     * @param tShareLimit 实例对象
     * @return 实例对象
     */
    @Override
    public TShareLimit save(TShareLimit tShareLimit) {
        TShareLimit tShareLimit1 = tShareLimitDao.queryById(tShareLimit.getDeviceId());
        if (tShareLimit1 != null){
            tShareLimit1.setLimits(tShareLimit.getLimits());
            tShareLimit1.setUpdateTime(new Date());
            tShareLimitDao.update(tShareLimit1);
            tShareLimit = tShareLimit1;
        }else {
            tShareLimitDao.insert(tShareLimit);
        }
        return tShareLimit;
    }

    /**
     * 修改数据
     *
     * @param tShareLimit 实例对象
     * @return 实例对象
     */
    @Override
    public TShareLimit update(TShareLimit tShareLimit) {
        this.tShareLimitDao.update(tShareLimit);
        return this.queryById(tShareLimit.getDeviceId());
    }

    /**
     * 通过主键删除数据
     *
     * @param deviceId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String deviceId) {
        return this.tShareLimitDao.deleteById(deviceId) > 0;
    }
}