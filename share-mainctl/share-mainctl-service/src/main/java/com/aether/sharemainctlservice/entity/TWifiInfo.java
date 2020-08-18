package com.aether.sharemainctlservice.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.io.Serializable;

/**
 * 热点信息(TWifiInfo)实体类
 *
 * @author 我走路带风
 * @since 2020-08-14 17:05:57
 */
public class TWifiInfo implements Serializable {
    private static final long serialVersionUID = 302431995523545456L;
    /**
    * 设备行记录id
    */
    @ApiParam(name = "设备唯一标识",required = true)
    private String deviceId;
    /**
    * 热点唯一标识
    */
    @ApiModelProperty(name = "热点唯一标识")
    private String bssid;
    /**
    * 热点名称
    */
    @ApiParam(name = "热点名称唯一标识",required = true)
    @ApiModelProperty(name = "热点唯名称",required = true)
    private String ssid;
    /**
    * 热点加密方式
    */
    @ApiParam(name = "热点加密方式",required = true)
    @ApiModelProperty(name = "热点加密方式",required = true)
    private String encryType;
    /**
    * 热点密码
    */
    @ApiParam(name = "热点密码",required = true)
    @ApiModelProperty(name = "热点密码",required = true)
    private String pwd;
    /**
    * 创建时间
    */
    @ApiModelProperty(hidden = true)
    private Date createTime;
    /**
    * 更新时间
    */
    @ApiModelProperty(hidden = true)
    private Date updateTime;


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

    public String getEncryType() {
        return encryType;
    }

    public void setEncryType(String encryType) {
        this.encryType = encryType;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}