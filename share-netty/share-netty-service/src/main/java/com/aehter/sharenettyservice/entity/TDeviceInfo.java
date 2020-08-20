package com.aehter.sharenettyservice.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 终端设备信息(TDeviceInfo)实体类
 *
 * @author 我走路带风
 * @since 2020-08-19 09:50:11
 */
public class TDeviceInfo implements Serializable {
    private static final long serialVersionUID = 483076799522005852L;
    /**
    * 设备唯一标识
    */
    private String deviceId;

    private String band;
    /**
    * 系统版本
    */
    private String sysV;
    /**
    * ui版本
    */
    private String uiV;
    /**
    * 注册时间
    */
    private Date registerTime;
    /**
    * 是否冻结，0：未冻结  1：已冻结
    */
    private Integer status;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getSysV() {
        return sysV;
    }

    public void setSysV(String sysV) {
        this.sysV = sysV;
    }

    public String getUiV() {
        return uiV;
    }

    public void setUiV(String uiV) {
        this.uiV = uiV;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}