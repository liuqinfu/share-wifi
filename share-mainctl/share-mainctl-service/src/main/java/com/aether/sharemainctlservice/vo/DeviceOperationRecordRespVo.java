package com.aether.sharemainctlservice.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luojianye
 * @description 设备操作记录响应的VO
 * @date 2020/8/19 14:21
 */
@Data
public class DeviceOperationRecordRespVo implements Serializable {

    private String deviceId;        // 设备唯一标识【引用t_device_info表的device_id字段值】
    private String operationType;   // 操作类型【WLAN：Wifi；bluetooth：蓝牙；hotspot：热点；GPS：GPS；APP：APP】
    private Integer action;         // 动作【0：关闭；1：打开；2：连接；4：退出】
    private String operationTime;   // 操作时间【格式：yyyy-MM-dd HH:mm:ss】
    private String operationData;   // 操作数据
    private Float longitude;        // GPS经度
    private Float latitude;         // GPS纬度
    private String gpsAddress;      // GPS地址【如：xxx省xxx市xxx】
    private String remarks;         // 备注说明

}
