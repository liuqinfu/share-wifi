package com.aether.sharemainctlservice.service.impl;

import com.aether.sharemainctlservice.entity.TOrderInfo;
import com.aether.sharemainctlservice.dao.TOrderInfoDao;
import com.aether.sharemainctlservice.service.TOrderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TOrderInfo)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-20 17:33:51
 */
@Service("tOrderInfoService")
public class TOrderInfoServiceImpl implements TOrderInfoService {
    @Resource
    private TOrderInfoDao tOrderInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TOrderInfo queryById(String id) {
        return this.tOrderInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TOrderInfo> queryAllByLimit(int offset, int limit) {
        return this.tOrderInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tOrderInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TOrderInfo insert(TOrderInfo tOrderInfo) {
        this.tOrderInfoDao.insert(tOrderInfo);
        return tOrderInfo;
    }

    /**
     * 修改数据
     *
     * @param tOrderInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TOrderInfo update(TOrderInfo tOrderInfo) {
        this.tOrderInfoDao.update(tOrderInfo);
        return this.queryById(tOrderInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tOrderInfoDao.deleteById(id) > 0;
    }
}