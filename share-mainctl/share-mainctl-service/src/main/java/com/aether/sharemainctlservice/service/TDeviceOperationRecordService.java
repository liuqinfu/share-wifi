package com.aether.sharemainctlservice.service;

import com.aether.sharemainctlservice.vo.DeviceOperationRecordReqVo;
import com.aether.sharemainctlservice.vo.DeviceOperationRecordRespVo;

import java.util.List;

/**
 * @author luojianye
 * @description 设备操作记录表（t_device_operation_record）服务类接口
 * @date 2020/8/19 14:17
 */
public interface TDeviceOperationRecordService {

    /**
     * 保存 设备操作记录表
     * @param vo
     * @return
     */
    boolean save(DeviceOperationRecordReqVo vo);

    /**
     * 根据条件 查找 设备操作记录
     * @param vo
     * @return
     */
    List<DeviceOperationRecordRespVo> findRecordsByCondition(DeviceOperationRecordReqVo vo);


}
