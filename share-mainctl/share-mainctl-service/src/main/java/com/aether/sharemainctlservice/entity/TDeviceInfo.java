package com.aether.sharemainctlservice.entity;

import com.aether.sharemainctlservice.service.impl.SDIServiceImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.io.Serializable;

/**
 * 终端设备信息(TDeviceInfo)实体类
 *
 * @author 我走路带风
 * @since 2020-08-14 13:22:13
 */
public class TDeviceInfo implements Serializable {
    private static final long serialVersionUID = -16456073654966172L;
    /**
    * 设备唯一标识
    */
    @NotNull(message = "设备标识不能为空")
    @ApiParam(value = "设备唯一标识",required = true)
    @ApiModelProperty("设备唯一标识")
    private String deviceId;
    /**
    * 系统版本
    */
    @NotNull(message = "系统版本不能为空")
    @ApiParam(value = "系统版本",required = true)
    @ApiModelProperty("系统版本")
    private String sysV;
    /**
    * ui版本
    */
    @NotNull(message = "系统UI版本不能为空")
    @ApiModelProperty("UI版本")
    @ApiParam(value = "UI版本",required = true)
    private String uiV;
    /**
    * 注册时间
    */

    @ApiParam(hidden = true)
    @ApiModelProperty("注册时间")
    private Date registerTime;
    /**
    * 是否冻结，0：未冻结  1：已冻结
    */
    @ApiParam(hidden = true)
    @ApiModelProperty("终端状态")
    private Integer status;

    @ApiModelProperty("SDI信息")
    private String sdiInfo;

    @ApiModelProperty("热点信息")
    private String apInfo;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public String getSdiInfo() {
        return sdiInfo;
    }

    public void setSdiInfo(String sdiInfo) {
        this.sdiInfo = sdiInfo;
    }

    public String getApInfo() {
        return apInfo;
    }

    public void setApInfo(String apInfo) {
        this.apInfo = apInfo;
    }
}