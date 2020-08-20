package com.aether.sharemainctlservice.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TSetmealInfo)实体类
 *
 * @author 我走路带风
 * @since 2020-08-20 17:26:43
 */
public class TSetmealInfo implements Serializable {
    private static final long serialVersionUID = 113562799963510156L;
    
    private String id;
    /**
    * 套餐名称
    */
    private String name;
    /**
    * 套餐容量；Kb
    */
    private Double flux;
    /**
    * 套餐价格、分
    */
    private Double price;
    /**
    * 有效期，天
    */
    private Date indate;
    /**
    * 套餐描述
    */
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFlux() {
        return flux;
    }

    public void setFlux(Double flux) {
        this.flux = flux;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}