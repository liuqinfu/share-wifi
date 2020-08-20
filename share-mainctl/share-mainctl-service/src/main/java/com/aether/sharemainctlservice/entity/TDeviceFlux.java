package com.aether.sharemainctlservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.io.Serializable;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)实体类
 *
 * @author 我走路带风
 * @since 2020-08-20 10:46:08
 */
@ApiModel(value = "设备流量记录")
public class TDeviceFlux implements Serializable {
    private static final long serialVersionUID = -47020127885430831L;
    /**
    * STA角色设备的device_id
    */
    @NotNull(message = "设备唯一标识不能为空")
    @ApiModelProperty(value = "设备唯一标识",required = true)
    private String deviceId;
    /**
    * 热点唯一标识
    */
    @ApiModelProperty(value = "热点唯一标识",required = false)
    private String bssid;
    /**
    * 热点名称
    */
    @NotNull(message = "热点名称不能为空")
    @ApiModelProperty(value = "热点名称",required = true)
    private String ssid;
    /**
    * 流量，单位Kb
    */
    @NotNull(message = "流量用量不能为空")
    @ApiModelProperty(value = "流量用量，单位kb",required = true)
    private Object flux;
    /**
    * 上报时间
    */
    @NotNull(message = "记录时间不能为空")
    @ApiModelProperty(value = "流量记录日期",required = true)
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