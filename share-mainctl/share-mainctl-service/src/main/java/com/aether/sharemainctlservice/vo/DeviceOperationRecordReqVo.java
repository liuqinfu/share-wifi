package com.aether.sharemainctlservice.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author luojianye
 * @description 设备操作记录请求的VO
 * @date 2020/8/19 14:21
 */
@Data
public class DeviceOperationRecordReqVo implements Serializable {
    // 新增
    @NotBlank(message = "deviceId不能为空")
    private String deviceId;        // 设备唯一标识【引用t_device_info表的device_id字段值】
    private String operationType;   // 操作类型【WLAN：Wifi；bluetooth：蓝牙；hotspot：热点；GPS：GPS；APP：APP】
    private Integer action;         // 动作【0：关闭；1：打开；2：连接；4：退出】
    private String operationData;   // 操作数据
    private Float longitude;        // GPS经度
    private Float latitude;         // GPS纬度
    private String gpsAddress;      // GPS地址【如：xxx省xxx市xxx】
    private String remarks;         // 备注说明

    // 查询
    private String startDate;       // 开始日期【格式：yyyy-MM-dd】
    private String endDate;         // 结束日期【格式：yyyy-MM-dd】
    private Integer offset;         // 查询记录的起始位置
    private Integer limit;          // 查询的记录条数

}
