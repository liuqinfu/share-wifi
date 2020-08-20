package com.aether.sharemainctlservice.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luojianye
 * @description 设备操作记录表（t_device_operation_record）实体类
 * @date 2020/8/19 13:34
 */
@Data
public class TDeviceOperationRecord implements Serializable {

    private Long id;                // 逻辑主键【自增列】
    private String deviceId;        // 设备唯一标识【引用t_device_info表的device_id字段值】
    private String operationType;   // 操作类型【WLAN：Wifi；bluetooth：蓝牙；hotspot：热点；GPS：GPS；APP：APP】
    private Integer action;         // 动作【0：关闭；1：打开；2：连接；4：退出】
    private Date operationTime;     // 操作时间
    private String operationData;   // 操作数据
    private Float longitude;        // GPS经度
    private Float latitude;         // GPS纬度
    private String gpsAddress;      // GPS地址【如：xxx省xxx市xxx】
    private String remarks;         // 备注说明
    private Date createdTime;       // 创建时间

}
