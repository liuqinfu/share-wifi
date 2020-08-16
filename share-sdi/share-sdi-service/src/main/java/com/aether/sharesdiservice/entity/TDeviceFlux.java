package com.aether.sharesdiservice.entity;

import io.swagger.annotations.ApiParam;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)实体类
 *
 * @author makejava
 * @since 2020-08-16 12:37:08
 */
public class TDeviceFlux implements Serializable {
    private static final long serialVersionUID = -29921617371459611L;
    /**
     * STA角色设备的device_id
     */
    @NotNull(message = "设备唯一标识不能为空")
    @ApiParam(name = "设备唯一标识",required = true)
    private String deviceId;
    /**
     * 热点唯一标识
     */
    @ApiParam(name = "热点唯一标识",required = false)
    private String bssid;
    /**
     * 热点名称
     */
    @NotNull(message = "热点名称不能为空")
    @ApiParam(name = "热点名称",required = true)
    private String ssid;
    /**
     * 流量，单位Kb
     */
    @NotNull(message = "流量用量不能为空")
    @ApiParam(name = "流量用量",required = true)
    private Object flux;
    /**
     * 上报时间
     */
    @NotNull(message = "上报时间不能为空")
    @ApiParam(name = "上报时间",required = true)
    private Date createTime = new Date();


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