package com.aehter.sharenettyservice.service.impl;

import com.aehter.sharenettyservice.entity.TDeviceInfo;
import com.aehter.sharenettyservice.dao.TDeviceInfoDao;
import com.aehter.sharenettyservice.service.TDeviceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 终端设备信息(TDeviceInfo)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-19 09:50:14
 */
@Service("tDeviceInfoService")
public class TDeviceInfoServiceImpl implements TDeviceInfoService {
    @Resource
    private TDeviceInfoDao tDeviceInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param deviceId 主键
     * @return 实例对象
     */
    @Override
    public TDeviceInfo queryById(String deviceId) {
        return this.tDeviceInfoDao.queryById(deviceId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TDeviceInfo> queryAllByLimit(int offset, int limit) {
        return this.tDeviceInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tDeviceInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TDeviceInfo insert(TDeviceInfo tDeviceInfo) {
        this.tDeviceInfoDao.insert(tDeviceInfo);
        return tDeviceInfo;
    }

    /**
     * 修改数据
     *
     * @param tDeviceInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TDeviceInfo update(TDeviceInfo tDeviceInfo) {
        this.tDeviceInfoDao.update(tDeviceInfo);
        return this.queryById(tDeviceInfo.getDeviceId());
    }

    /**
     * 通过主键删除数据
     *
     * @param deviceId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String deviceId) {
        return this.tDeviceInfoDao.deleteById(deviceId) > 0;
    }
}