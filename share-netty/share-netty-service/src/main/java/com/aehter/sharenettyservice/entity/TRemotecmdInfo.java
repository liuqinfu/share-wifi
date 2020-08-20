package com.aehter.sharenettyservice.entity;

import java.io.Serializable;

/**
 * (TRemotecmdInfo)实体类
 *
 * @author 我走路带风
 * @since 2020-08-19 09:57:46
 */
public class TRemotecmdInfo implements Serializable {
    private static final long serialVersionUID = -48441162830277422L;
    
    private Integer id;
    /**
    * 手机品牌
    */
    private String deviceBand;
    /**
    * 操作系统版本
    */
    private String sysVersion;
    /**
    * UI版本
    */
    private String uiVersion;
    /**
    * 远程指令需要按执行顺序存储，结构为json
    */
    private String remoteCmds;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceBand() {
        return deviceBand;
    }

    public void setDeviceBand(String deviceBand) {
        this.deviceBand = deviceBand;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public String getUiVersion() {
        return uiVersion;
    }

    public void setUiVersion(String uiVersion) {
        this.uiVersion = uiVersion;
    }

    public String getRemoteCmds() {
        return remoteCmds;
    }

    public void setRemoteCmds(String remoteCmds) {
        this.remoteCmds = remoteCmds;
    }

}