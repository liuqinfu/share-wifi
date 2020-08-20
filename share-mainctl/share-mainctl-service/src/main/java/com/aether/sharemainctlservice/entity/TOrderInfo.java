package com.aether.sharemainctlservice.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TOrderInfo)实体类
 *
 * @author 我走路带风
 * @since 2020-08-20 17:33:49
 */
public class TOrderInfo implements Serializable {
    private static final long serialVersionUID = -58285142698752322L;
    
    private String id;
    /**
    * 设备id
    */
    private String deviceId;
    /**
    * 套餐id
    */
    private String mealId;
    /**
    * 支付价格
    */
    private Double payPrice;
    /**
    * 订单状态  1、已创建、待支付；2、已创建、已支付；3、已创建、已取消
    */
    private Integer status;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
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

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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