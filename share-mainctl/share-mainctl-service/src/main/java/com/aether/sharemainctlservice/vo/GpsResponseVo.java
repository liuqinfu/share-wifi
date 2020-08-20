package com.aether.sharemainctlservice.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luojianye
 * @description 查询GPS信息 响应使用的VO
 * @date 2020/8/18 17:23
 */
@Data
public class GpsResponseVo implements Serializable {
    private String deviceId;    // 设备唯一标识
    private Double latitude;    // 纬度
    private Double longitude;   // 经度
    private String createTime;  // 上报时间【格式：yyyy-MM-dd HH:mm:ss】

}
