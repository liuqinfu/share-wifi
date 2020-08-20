package com.aether.sharemainctlservice.service.impl;

import com.aether.sharecommon.utils.DateUtils;
import com.aether.sharemainctlservice.entity.TGpsHis;
import com.aether.sharemainctlservice.dao.TGpsHisDao;
import com.aether.sharemainctlservice.service.TGpsHisService;
import com.aether.sharemainctlservice.vo.GpsRequestVo;
import com.aether.sharemainctlservice.vo.GpsResponseVo;
import jdk.internal.org.objectweb.asm.Handle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 终端设备位置表(TGpsHis)表服务实现类
 *
 * @author 我走路带风
 * @since 2020-08-14 17:00:28
 */
@Slf4j
@Service("tGpsHisService")
public class TGpsHisServiceImpl implements TGpsHisService {
    @Resource
    private TGpsHisDao tGpsHisDao;


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
        return null;
    }



    @Override
    public List<GpsResponseVo> findGpsByCondition(GpsRequestVo vo) {
        log.info(" TGpsHisServiceImpl findGpsByCondition GpsRequestVo vo={}", vo);
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("deviceId", vo.getDeviceId());
        if(StringUtils.hasText(vo.getStartDate())) {
            conditionMap.put("startTime", (vo.getStartDate() + " 00:00:00"));
        }
        if(StringUtils.hasText(vo.getEndDate())) {
            conditionMap.put("endTime", (vo.getEndDate()+ " 23:59:59"));
        }
        conditionMap.put("offset", vo.getOffset());
        conditionMap.put("limit", vo.getLimit());
        log.info(" TGpsHisServiceImpl findGpsByCondition Map<String, Object> conditionMap={}", conditionMap);
        List<GpsResponseVo> resultList = new ArrayList<>();
        List<TGpsHis> gpsList = tGpsHisDao.findGpsByCondition(conditionMap);
        if(!CollectionUtils.isEmpty(gpsList)){
            gpsList.stream().forEach(item -> {
                GpsResponseVo respVo = new GpsResponseVo();
                respVo.setDeviceId(item.getDeviceId());
                respVo.setLatitude(item.getLatitude());
                respVo.setLongitude(item.getLongitude());
                respVo.setCreateTime(DateUtils.formatDate(item.getCreateTime(), DateUtils.yyyy_MM_dd_HH_mm_ss));
                resultList.add(respVo);
            });
        }
        return resultList;
    }



}