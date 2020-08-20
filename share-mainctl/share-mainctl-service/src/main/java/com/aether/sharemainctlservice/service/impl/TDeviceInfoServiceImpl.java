package com.aether.sharemainctlservice.service.impl;

import com.aether.sharecommon.utils.RedisUtil;
import com.aether.sharemainctlservice.dao.TGpsHisDao;
import com.aether.sharemainctlservice.entity.TDeviceInfo;
import com.aether.sharemainctlservice.dao.TDeviceInfoDao;
import com.aether.sharemainctlservice.entity.TGpsHis;
import com.aether.sharemainctlservice.service.TDeviceInfoService;
import com.aether.sharemainctlservice.thirdparty.OtherOuterApi;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 终端设备信息(TDeviceInfo)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-14 13:22:19
 */
@Service("tDeviceInfoService")
public class TDeviceInfoServiceImpl implements TDeviceInfoService {

//    private volatile static int count = 0;

    @Resource
    private TDeviceInfoDao tDeviceInfoDao;

    @Resource
    private TGpsHisDao tGpsHisDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private OtherOuterApi otherOuterApi;

    /**
     * STA终端信息
     */
    @Value("${redis.keys.netty}")
    private String onlineDeviceTokens;

    @Value("${url.greaterwifi}")
    private String getGreatWifiUrl;

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
     * 查询所有设备在线离线状态
     * @return
     */
    @Override
    public List<TDeviceInfo> queryDevicesOnlinesOr() {
        Map<Object, Object> hmget = redisUtil.hmget(onlineDeviceTokens);
        List<Object> tokens = hmget.keySet().stream().collect(Collectors.toList());
        List<TDeviceInfo> tDeviceInfos = this.tDeviceInfoDao.queryByIds(tokens);
        return tDeviceInfos;
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
            tGpsHisDao.insert(tGpsHis);
        } else {
            //注册
            tDeviceInfo.setRegisterTime(new Date());
            tDeviceInfo.setStatus(0);
            tDeviceInfoDao.insert(tDeviceInfo);
        }
        //获取最优热点
        Map greaterWIFI = otherOuterApi.getGreaterWIFI(getGreatWifiUrl, tGpsHis.getLatitude(), tGpsHis.getLongitude());
        tDeviceInfo.setApInfo(JSONObject.toJSONString(greaterWIFI));
        //获取SDI
        Map<Object, Object> sdi_balance_load = redisUtil.hmget("MAIN_CTL");
        List<Map.Entry<String, Integer>> sdi_balances = sortASC(sdi_balance_load);
        Map.Entry<String, Integer> sdi_loads_map = sdi_balances.stream().findFirst().get();
        String sdi_Addr = sdi_loads_map.getKey(); //ip:port
        Integer sdi_loads = sdi_loads_map.getValue();
        tDeviceInfo.setSdiInfo(sdi_Addr);
        return tDeviceInfo;

    }

    /**
     * 对SDIBALANCER进行排序，升序
     * @param source
     * @return
     */
    public List<Map.Entry<String,Integer>> sortASC(Map source){
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(source.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });
        return list;
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