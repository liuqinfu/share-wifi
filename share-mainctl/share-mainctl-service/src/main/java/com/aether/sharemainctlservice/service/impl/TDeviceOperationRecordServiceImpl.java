package com.aether.sharemainctlservice.service.impl;

import com.aether.sharecommon.utils.DateUtils;
import com.aether.sharemainctlservice.dao.TDeviceOperationRecordDao;
import com.aether.sharemainctlservice.entity.TDeviceOperationRecord;
import com.aether.sharemainctlservice.service.TDeviceOperationRecordService;
import com.aether.sharemainctlservice.vo.DeviceOperationRecordReqVo;
import com.aether.sharemainctlservice.vo.DeviceOperationRecordRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author luojianye
 * @description 设备操作记录表（t_device_operation_record）服务类接口的实现类
 * @date 2020/8/19 14:29
 */
@Slf4j
@Service
public class TDeviceOperationRecordServiceImpl implements TDeviceOperationRecordService {

    @Autowired
    private TDeviceOperationRecordDao tDeviceOperationRecordDao;

    @Override
    public boolean save(DeviceOperationRecordReqVo vo) {
        log.info(" TDeviceOperationRecordServiceImpl save DeviceOperationRecordReqVo vo={}", vo);
        TDeviceOperationRecord entity = new TDeviceOperationRecord();
        entity.setDeviceId(vo.getDeviceId());
        entity.setOperationType(vo.getOperationType());
        entity.setAction(vo.getAction());
        entity.setOperationData(vo.getOperationData());
        entity.setLongitude(vo.getLongitude());
        entity.setLatitude(vo.getLatitude());
        entity.setGpsAddress(vo.getGpsAddress());
        entity.setRemarks(vo.getRemarks());
        Date now = new Date();
        entity.setOperationTime(now);
        entity.setCreatedTime(now);
        log.info(" TDeviceOperationRecordServiceImpl save TDeviceOperationRecord entity={}", entity);
        int rows = tDeviceOperationRecordDao.save(entity);
        if(rows > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<DeviceOperationRecordRespVo> findRecordsByCondition(DeviceOperationRecordReqVo vo) {
        log.info(" TDeviceOperationRecordServiceImpl findRecordsByCondition DeviceOperationRecordReqVo vo={}", vo);
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("deviceId", vo.getDeviceId());
        conditionMap.put("operationType", vo.getOperationType());
        if(StringUtils.hasText(vo.getStartDate())) {
            conditionMap.put("startTime", (vo.getStartDate() + " 00:00:00"));
        }
        if(StringUtils.hasText(vo.getEndDate())) {
            conditionMap.put("endTime", (vo.getEndDate()+ " 23:59:59"));
        }
        conditionMap.put("offset", vo.getOffset());
        conditionMap.put("limit", vo.getLimit());
        log.info(" TDeviceOperationRecordServiceImpl findRecordsByCondition Map<String, Object> conditionMap={}", conditionMap);
        List<DeviceOperationRecordRespVo> resultList = new ArrayList<>();
        List<TDeviceOperationRecord> list = tDeviceOperationRecordDao.findRecordsByCondition(conditionMap);
        if(!CollectionUtils.isEmpty(list)){
            list.stream().forEach(item -> {
                DeviceOperationRecordRespVo respVo = new DeviceOperationRecordRespVo();
                respVo.setDeviceId(item.getDeviceId());
                respVo.setOperationType(item.getOperationType());
                respVo.setAction(item.getAction());
                respVo.setOperationTime(DateUtils.formatDate(item.getOperationTime(), DateUtils.yyyy_MM_dd_HH_mm_ss));
                respVo.setOperationData(item.getOperationData());
                respVo.setLongitude(item.getLongitude());
                respVo.setLatitude(item.getLatitude());
                respVo.setGpsAddress(item.getGpsAddress());
                respVo.setRemarks(item.getRemarks());
                resultList.add(respVo);
            });
        }
        return resultList;
    }

}
