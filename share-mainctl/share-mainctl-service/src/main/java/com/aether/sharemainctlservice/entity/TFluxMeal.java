package com.aether.sharemainctlservice.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TFluxMeal)实体类
 *
 * @author 我走路带风
 * @since 2020-08-20 17:32:28
 */
public class TFluxMeal implements Serializable {
    private static final long serialVersionUID = -85433710784196185L;
    
    private String id;
    /**
    * 设备唯一标识
    */
    private String deviceId;
    /**
    * 订单id
    */
    private String orderId;
    /**
    * 已使用流量 Kb
    */
    private Object usedFlux;
    /**
    * 剩余流量  Kb
    */
    private Object leftFlux;
    /**
    * 套餐状态  1：使用中、2：使用完、3：已失效
    */
    private Integer status;
    /**
    * 失效时间
    */
    private Date invildTime;
    
    private Date createTime;
    
    private Date updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Object getUsedFlux() {
        return usedFlux;
    }

    public void setUsedFlux(Object usedFlux) {
        this.usedFlux = usedFlux;
    }

    public Object getLeftFlux() {
        return leftFlux;
    }

    public void setLeftFlux(Object leftFlux) {
        this.leftFlux = leftFlux;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInvildTime() {
        return invildTime;
    }

    public void setInvildTime(Date invildTime) {
        this.invildTime = invildTime;
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