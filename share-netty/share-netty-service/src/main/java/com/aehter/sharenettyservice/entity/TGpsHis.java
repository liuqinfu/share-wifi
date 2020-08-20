package com.aehter.sharenettyservice.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 终端设备位置表(TGpsHis)实体类
 *
 * @author 我走路带风
 * @since 2020-08-19 09:55:05
 */
public class TGpsHis implements Serializable {
    private static final long serialVersionUID = 210129339368840806L;
    /**
    * 设备唯一标识
    */
    private String deviceId;
    /**
    * 纬度
    */
    private Double latitude;
    /**
    * 经度
    */
    private Double longitude;
    /**
    * 上报时间
    */
    private Date createTime;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}