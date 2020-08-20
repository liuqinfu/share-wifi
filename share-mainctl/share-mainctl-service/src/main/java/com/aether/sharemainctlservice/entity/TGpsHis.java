package com.aether.sharemainctlservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.io.Serializable;

/**
 * 终端设备位置表(TGpsHis)实体类
 *
 * @author 我走路带风
 * @since 2020-08-14 17:00:28
 */
@ApiModel(description = "设备位置信息")
public class TGpsHis implements Serializable {
    private static final long serialVersionUID = 590106256924026728L;
    /**
    * 设备唯一标识
    */
    @NotNull(message = "设备唯一标识不能为空")
    @ApiModelProperty(notes = "设备唯一标识",required = true)
    private String deviceId;
    /**
    * 纬度
    */
    @NotNull(message = "设备纬度不能为空")
    @ApiModelProperty(notes = "纬度",required = true)
    private Double latitude;
    /**
    * 经度
    */
    @NotNull(message = "设备经度不能为空")
    @ApiModelProperty(notes = "经度",required = true)
    private Double longitude;
    /**
    * 上报时间
    */
    @ApiModelProperty(hidden = true)
    private Date createTime = new Date();


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