package com.aether.xxljob.executor.service.impl;

import com.aether.xxljob.executor.entity.TFluxMeal;
import com.aether.xxljob.executor.dao.TFluxMealDao;
import com.aether.xxljob.executor.service.TFluxMealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TFluxMeal)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-24 10:34:59
 */
@Service("tFluxMealService")
public class TFluxMealServiceImpl implements TFluxMealService {
    @Resource
    private TFluxMealDao tFluxMealDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TFluxMeal queryById(String id) {
        return this.tFluxMealDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TFluxMeal> queryAllByLimit(int offset, int limit) {
        return this.tFluxMealDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<TFluxMeal> queryAllValidByDeviceId(String deviceId) {
        return this.tFluxMealDao.queryAllValidByDeviceId(deviceId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<String> queryAllValid() {
        return this.tFluxMealDao.queryAllValid();
    }

    /**
     * 新增数据
     *
     * @param tFluxMeal 实例对象
     * @return 实例对象
     */
    @Override
    public TFluxMeal insert(TFluxMeal tFluxMeal) {
        this.tFluxMealDao.insert(tFluxMeal);
        return tFluxMeal;
    }

    /**
     * 修改数据
     *
     * @param tFluxMeal 实例对象
     * @return 实例对象
     */
    @Override
    public TFluxMeal update(TFluxMeal tFluxMeal) {
        this.tFluxMealDao.update(tFluxMeal);
        return this.queryById(tFluxMeal.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tFluxMealDao.deleteById(id) > 0;
    }
}