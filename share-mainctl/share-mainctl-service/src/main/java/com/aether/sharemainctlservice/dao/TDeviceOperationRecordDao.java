package com.aether.sharemainctlservice.dao;

import com.aether.sharemainctlservice.entity.TDeviceOperationRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author luojianye
 * @description 设备操作记录表（t_device_operation_record）DAO
 * @date 2020/8/19 13:33
 */
//@Mapper
public interface TDeviceOperationRecordDao {

    /**
     * 保存 设备操作记录
     * @param entity
     * @return
     */
    int save(TDeviceOperationRecord entity);

    /**
     * 根据条件 查询 设备操作记录
     * @param conditionMap
     * @return
     */
    List<TDeviceOperationRecord> findRecordsByCondition(Map<String, Object> conditionMap);

}
