package com.aether.xxljob.executor.service.impl;

import com.aether.xxljob.executor.entity.TDeviceFlux;
import com.aether.xxljob.executor.dao.TDeviceFluxDao;
import com.aether.xxljob.executor.service.TDeviceFluxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-24 10:11:01
 */
@Service("tDeviceFluxService")
public class TDeviceFluxServiceImpl implements TDeviceFluxService {
    @Resource
    private TDeviceFluxDao tDeviceFluxDao;


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TDeviceFlux> queryAllByLimit(int offset, int limit) {
        return this.tDeviceFluxDao.queryAllByLimit(offset, limit);
    }

    @Override
    public long sumFluxByBeviceIdandTime(String deviceId, Date startTime) {
        return this.tDeviceFluxDao.sumFluxByBeviceIdandTime(deviceId,startTime);
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