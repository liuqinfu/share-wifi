package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.PubFinals;
import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharecommon.utils.DateUtils;
import com.aether.sharemainctlservice.exception.ParameterException;
import com.aether.sharemainctlservice.service.TDeviceOperationRecordService;
import com.aether.sharemainctlservice.vo.DeviceOperationRecordReqVo;
import com.aether.sharemainctlservice.vo.DeviceOperationRecordRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author luojianye
 * @description 设备操作记录表（t_device_operation_record）的控制器层
 * @date 2020/8/19 14:45
 */
@Slf4j
@RestController
@RequestMapping("/deviceOperationRecord")
public class TDeviceOperationRecordController {

    @Autowired
    private TDeviceOperationRecordService tDeviceOperationRecordService;

    /**
     * 保存 设备操作记录表
     * @param vo
     * @return
     */
    @PostMapping("/save")
    public ResultVO save(@RequestBody DeviceOperationRecordReqVo vo){
        String operationType = vo.getOperationType();
        if(!StringUtils.hasText(operationType)){
            throw new ParameterException("operationType不能为空");
        }
        Integer action = vo.getAction();
        if(Objects.isNull(action)){
            throw new ParameterException("action不能为空");
        }
        boolean isSuccess = tDeviceOperationRecordService.save(vo);
        if(isSuccess){
            return new ResultVO(ResultCode.SUCCESS);
        }
        return new ResultVO(ResultCode.FAILED);
    }

    /**
     * 根据条件 查找 设备操作记录
     * @param vo
     * @return
     */
    @PostMapping("/findRecords")
    public ResultVO<List<DeviceOperationRecordRespVo>> findRecordsByCondition(@RequestBody @Valid DeviceOperationRecordReqVo vo){
        String startDate = vo.getStartDate();
        if(StringUtils.hasText(startDate)){
            if(startDate.length() != PubFinals.INT_10){
                throw new ParameterException("startDate日期格式错误【格式：yyyy-MM-dd】");
            }
            Date sDate = DateUtils.convertDateFormat(startDate, DateUtils.yyyy_MM_dd);
            if(Objects.isNull(sDate)){
                throw new ParameterException("startDate日期格式错误【格式：yyyy-MM-dd】");
            }
        }
        String endDate = vo.getEndDate();
        if(StringUtils.hasText(endDate)){
            if(endDate.length() != PubFinals.INT_10){
                throw new ParameterException("endDate日期格式错误【格式：yyyy-MM-dd】");
            }
            Date eDate = DateUtils.convertDateFormat(endDate, DateUtils.yyyy_MM_dd);
            if(Objects.isNull(eDate)){
                throw new ParameterException("endDate日期格式错误【格式：yyyy-MM-dd】");
            }
        }
        List<DeviceOperationRecordRespVo> list = tDeviceOperationRecordService.findRecordsByCondition(vo);
        return new ResultVO<>(ResultCode.SUCCESS, list);
    }


}
