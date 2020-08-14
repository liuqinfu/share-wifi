package com.aether.sharemainctlservice.service.impl;

import com.aether.sharemainctlservice.entity.TWifiInfo;
import com.aether.sharemainctlservice.dao.TWifiInfoDao;
import com.aether.sharemainctlservice.service.TWifiInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 热点信息(TWifiInfo)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-14 17:05:57
 */
@Service("tWifiInfoService")
public class TWifiInfoServiceImpl implements TWifiInfoService {
    @Resource
    private TWifiInfoDao tWifiInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param deviceId 主键
     * @return 实例对象
     */
    @Override
    public TWifiInfo queryById(String deviceId) {
        return this.tWifiInfoDao.queryById(deviceId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TWifiInfo> queryAllByLimit(int offset, int limit) {
        return this.tWifiInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tWifiInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TWifiInfo insert(TWifiInfo tWifiInfo) {
        this.tWifiInfoDao.insert(tWifiInfo);
        return tWifiInfo;
    }

    /**
     * 修改数据
     *
     * @param tWifiInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TWifiInfo update(TWifiInfo tWifiInfo) {
        this.tWifiInfoDao.update(tWifiInfo);
        return this.queryById(tWifiInfo.getDeviceId());
    }

    /**
     * 通过主键删除数据
     *
     * @param deviceId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String deviceId) {
        return this.tWifiInfoDao.deleteById(deviceId) > 0;
    }
}