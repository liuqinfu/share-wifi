package com.aether.sharesdiservice.service.impl;

import com.aether.sharesdiservice.dao.TDeviceFluxDao;
import com.aether.sharesdiservice.entity.TDeviceFlux;
import com.aether.sharesdiservice.service.TDeviceFluxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)表服务实现类
 *
 * @author makejava
 * @since 2020-08-16 12:37:26
 */
@Service("tDeviceFluxService")
public class TDeviceFluxServiceImpl implements TDeviceFluxService {
    @Resource
    private TDeviceFluxDao tDeviceFluxDao;

    /**
     * 通过ID查询单条数据
     *
     * @param params 参数
     * @return 实例对象
     */
    @Override
    public List<Map> countByParam(Map params) {
        return this.tDeviceFluxDao.countByParam(params);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TDeviceFlux> queryAllByLimit(int offset, int limit) {
        return this.tDeviceFluxDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tDeviceFlux 实例对象
     * @return 实例对象
     */
    @Override
    public TDeviceFlux insert(TDeviceFlux tDeviceFlux) {
        this.tDeviceFluxDao.insert(tDeviceFlux);
        return tDeviceFlux;
    }

}