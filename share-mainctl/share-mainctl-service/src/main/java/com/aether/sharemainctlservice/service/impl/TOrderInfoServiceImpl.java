package com.aether.sharemainctlservice.service.impl;

import com.aether.sharecommon.utils.StringUtil;
import com.aether.sharemainctlservice.dao.TFluxMealDao;
import com.aether.sharemainctlservice.dao.TSetmealInfoDao;
import com.aether.sharemainctlservice.entity.TFluxMeal;
import com.aether.sharemainctlservice.entity.TOrderInfo;
import com.aether.sharemainctlservice.dao.TOrderInfoDao;
import com.aether.sharemainctlservice.entity.TSetmealInfo;
import com.aether.sharemainctlservice.exception.APIException;
import com.aether.sharemainctlservice.service.TOrderInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private TSetmealInfoDao tSetmealInfoDao;
    @Resource
    private TFluxMealDao tFluxMealDao;

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
     * 初始化订单
     * @param deviceId 设备唯一标识
     * @param mealId  套餐id
     * @return
     */
    @Override
    public TOrderInfo initOrder(String deviceId, String mealId) {
        TSetmealInfo tSetmealInfo = tSetmealInfoDao.queryById(mealId);
        if (tSetmealInfo == null)return null;
        Date date = new Date();
        TOrderInfo tOrderInfo = new TOrderInfo()
                .setId(StringUtil.get32GUID())
                .setDeviceId(deviceId)
                .setMealId(mealId)
                .setPayPrice(tSetmealInfo.getPrice())
                .setStatus(1)
                .setCreateTime(date)
                .setUpdateTime(date);
        tOrderInfoDao.insert(tOrderInfo);
        return tOrderInfo;
    }

    /**
     * 支付订单
     * @param deviceId 设备唯一标识
     * @param orderId  订单id
     * @return
     */
    @Transactional
    @Override
    public void payOrder(String deviceId, String orderId) {
        Map param = new HashMap();
        param.put("id",orderId);
        param.put("deviceId",deviceId);
        param.put("status",1);
        List<TOrderInfo> tOrderInfos = tOrderInfoDao.queryAll(param);
        if (tOrderInfos != null || tOrderInfos.size() == 0) throw new IllegalArgumentException("无效订单");
        TOrderInfo tOrderInfo = tOrderInfos.get(0);
        tOrderInfo.setStatus(2).setUpdateTime(new Date());
        tOrderInfoDao.update(tOrderInfo);
        //下单成功之后  将套餐加入到用户套餐表中
        ZonedDateTime start = ZonedDateTime.now();
        ZonedDateTime now = ZonedDateTime.now();
        // 1、得到当前套餐的开始时间，以计算套餐结束时间
        TFluxMeal tFluxMeal = tFluxMealDao.queryLastValidMeal(deviceId);
        if (tFluxMeal != null) start = tFluxMeal.getInvildTime();
        //计算新套餐结束时间
        String mealId = tOrderInfo.getMealId();
        TSetmealInfo tSetmealInfo = tSetmealInfoDao.queryById(mealId);
        BigInteger indate = tSetmealInfo.getIndate();//单位是天
        TFluxMeal tFluxMeal1 = new TFluxMeal()
                .setId(StringUtil.get32GUID())
                .setDeviceId(deviceId)
                .setOrderId(tOrderInfo.getId())
                .setUsedFlux(0)
                .setLeftFlux(tSetmealInfo.getFlux())
                .setStartTime(start)
                .setInvildTime(start.plusDays(indate.longValue()))
                .setStatus(1)
                .setCreateTime(now)
                .setUpdateTime(now);
        // 2、将当前套餐累积到用户套餐中
        tFluxMealDao.insert(tFluxMeal1);
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
     * 查询多条数据
     * @param param 参数
     * @return 对象列表
     */
    @Override
    public List<TOrderInfo> queryAll(Map param) {
        List<TOrderInfo> tOrderInfos = this.tOrderInfoDao.queryAll(param);
        return tOrderInfos;
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