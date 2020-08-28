package com.aether.xxljob.executor.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)实体类
 *
 * @author 我走路带风
 * @since 2020-08-24 10:11:01
 */
public class TDeviceFlux implements Serializable {
    private static final long serialVersionUID = -78062976645092727L;
    /**
    * STA角色设备的device_id
    */
    private String deviceId;
    /**
    * 热点唯一标识
    */
    private String bssid;
    /**
    * 热点名称
    */
    private String ssid;
    /**
    * 流量，单位Kb
    */
    private Object flux;
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

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public Object getFlux() {
        return flux;
    }

    public void setFlux(Object flux) {
        this.flux = flux;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}