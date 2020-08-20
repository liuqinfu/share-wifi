package com.aehter.sharenettyservice.service.impl;

import com.aehter.sharenettyservice.dao.TDeviceInfoDao;
import com.aehter.sharenettyservice.entity.TDeviceInfo;
import com.aehter.sharenettyservice.entity.TRemotecmdInfo;
import com.aehter.sharenettyservice.dao.TRemotecmdInfoDao;
import com.aehter.sharenettyservice.service.TRemotecmdInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TRemotecmdInfo)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-19 09:57:46
 */
@Service("tRemotecmdInfoService")
public class TRemotecmdInfoServiceImpl implements TRemotecmdInfoService {
    @Resource
    private TRemotecmdInfoDao tRemotecmdInfoDao;
    @Resource
    private TDeviceInfoDao tDeviceInfoDao;

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
     * 通过设备标识查询单条数据
     *
     * @param deviceId 设备唯一标识
     * @return 实例对象
     */
    @Override
    public TRemotecmdInfo queryByDeviceId(String  deviceId) {
        TDeviceInfo tDeviceInfo = tDeviceInfoDao.queryById(deviceId);
        Map params = new HashMap();
        params.put("deviceBand",tDeviceInfo.getBand());
        params.put("sysVersion",tDeviceInfo.getSysV());
        params.put("uiVersion",tDeviceInfo.getUiV());
        TRemotecmdInfo tRemotecmdInfo = tRemotecmdInfoDao.queryByParams(params);
        return tRemotecmdInfo;
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