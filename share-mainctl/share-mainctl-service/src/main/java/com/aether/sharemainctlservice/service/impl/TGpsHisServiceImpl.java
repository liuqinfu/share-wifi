package com.aether.sharemainctlservice.service.impl;

import com.aether.sharemainctlservice.entity.TGpsHis;
import com.aether.sharemainctlservice.dao.TGpsHisDao;
import com.aether.sharemainctlservice.service.TGpsHisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 终端设备位置表(TGpsHis)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-14 17:00:28
 */
@Service("tGpsHisService")
public class TGpsHisServiceImpl implements TGpsHisService {
    @Resource
    private TGpsHisDao tGpsHisDao;

    /**
     * 通过ID查询单条数据
     *
     * @param  主键
     * @return 实例对象
     */
    @Override
    public TGpsHis queryById( ) {
        return this.tGpsHisDao.queryById();
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TGpsHis> queryAllByLimit(int offset, int limit) {
        return this.tGpsHisDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tGpsHis 实例对象
     * @return 实例对象
     */
    @Override
    public TGpsHis insert(TGpsHis tGpsHis) {
        this.tGpsHisDao.insert(tGpsHis);
        return tGpsHis;
    }

    /**
     * 修改数据
     *
     * @param tGpsHis 实例对象
     * @return 实例对象
     */
    @Override
    public TGpsHis update(TGpsHis tGpsHis) {
        this.tGpsHisDao.update(tGpsHis);
        return this.queryById(tGpsHis.get());
    }

    /**
     * 通过主键删除数据
     *
     * @param  主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById( ) {
        return this.tGpsHisDao.deleteById() > 0;
    }
}