package com.aehter.sharenettyservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * (TOrderInfo)实体类
 *
 * @author 我走路带风
 * @since 2020-07-23 09:41:25
 */
@ApiModel(value = "远程指令",description = "向终端下达远程指令")
@Data
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = -18039374005860139L;

    @NotNull(message = "设备id不能为空")
    @ApiModelProperty(value = "终端设备唯一标识",notes = "设备id",required = true)
    private String deviceId;

    /**
     * 指令类型   WIFI BLUETOOTH  HOTSPOT
     */
    @Pattern(regexp = "WIFI|BLUETOOTH|HOTSPOT",message = "指令类型仅支持 WIFI BLUETOOTH  HOTSPOT")
    @ApiModelProperty(value = "指令类型",allowableValues = "WIFI,BLUETOOTH,HOTSPOT",required = true)
    private String orderName;

    /**
     * 操作类型  ON  OFF  CONNECT
     */
    @Pattern(regexp = "ON|OFF|CONNECT",message = "操作类型仅支持 ON  OFF  CONNECT")
    @ApiModelProperty(value = "操作类型",allowableValues = "ON,OFF,CONNECT",required = true)
    private String option;


    /**
     * 当连接蓝牙或热点时，描述目标信息
     * 当order为connect状态时   不能为空
     */
    @ApiModelProperty(value = "连接目标信息",required = true)
    private ConnectTargetInfo connectTargetInfo;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public ConnectTargetInfo getConnectTargetInfo() {
        return connectTargetInfo;
    }

    public void setConnectTargetInfo(ConnectTargetInfo connectTargetInfo) {
        this.connectTargetInfo = connectTargetInfo;
    }
}

@ApiModel(value = "连接对象",description = "蓝牙或热点得连接对象")
@Data
class ConnectTargetInfo{

    /**
     * 连接目标名称
     */
    @NotNull(message = "连接名称不能为空")
    @ApiModelProperty(value = "目标名称",required = true)
    private String target;

    /**
     * 加密方式
     */
    @ApiModelProperty(value = "热点加密方式",required = false)
    private String encrypt;

    /**
     * 连接密码
     */
    @ApiModelProperty(value = "密码",required = false)
    private String password;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}