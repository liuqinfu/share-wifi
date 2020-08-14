package com.aether.sharemainctlservice.service.impl;

import com.aether.sharecommon.utils.RedisUtil;
import com.aether.sharemainctlservice.entity.TDeviceInfo;
import com.aether.sharemainctlservice.dao.TDeviceInfoDao;
import com.aether.sharemainctlservice.entity.TGpsHis;
import com.aether.sharemainctlservice.service.TDeviceInfoService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 终端设备信息(TDeviceInfo)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-14 13:22:19
 */
@Service("tDeviceInfoService")
public class TDeviceInfoServiceImpl implements TDeviceInfoService {

    private volatile static int count = 0;

    @Resource
    private TDeviceInfoDao tDeviceInfoDao;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${sdi.redis.reg}")
    private String sdi_reg;

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
     * 登陆或注册
     *
     * @param tDeviceInfo 终端设备信息
     * @param tGpsHis     终端设备GPS
     * @return 实例对象
     */
    @Override
    public TDeviceInfo regLogin(TDeviceInfo tDeviceInfo, TGpsHis tGpsHis) {
        TDeviceInfo tDeviceInfo1 = tDeviceInfoDao.queryById(tDeviceInfo.getDeviceId());
        if (tDeviceInfo != null) {
            //登陆
            // 更新系统版本  UI版本信息
            tDeviceInfo1.setSysV(tDeviceInfo.getSysV());
            tDeviceInfo1.setUiV(tDeviceInfo.getUiV());
            tDeviceInfo.setRegisterTime(tDeviceInfo1.getRegisterTime());
            tDeviceInfo.setStatus(tDeviceInfo1.getStatus());
            tDeviceInfoDao.update(tDeviceInfo1);
        } else {
            //注册
            tDeviceInfo.setRegisterTime(new Date());
            tDeviceInfo.setStatus(0);
            tDeviceInfoDao.insert(tDeviceInfo);
        }
        //获取最优热点


        //获取SDI
        long setSize = redisUtil.sGetSetSize(sdi_reg);
        Set<Object> objects = redisUtil.sGet(sdi_reg);
        Integer index = Math.toIntExact(count % setSize);
        String sdiInfoStr = (String) (objects.stream().toArray())[index];
        SDIServiceImpl.SDIInfo sdiInfo = JSONObject.parseObject(sdiInfoStr, SDIServiceImpl.SDIInfo.class);
        tDeviceInfo.setSdiInfo(sdiInfo.toString());
        return tDeviceInfo;

    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
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