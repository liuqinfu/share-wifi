package com.aether.sharemainctlservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigInteger;
import java.util.Date;
import java.io.Serializable;

/**
 * (TSetmealInfo)实体类
 *
 * @author 我走路带风
 * @since 2020-08-20 17:26:43
 */
@ApiModel(value = "流量套餐",description = "共享流量套餐")
public class TSetmealInfo implements Serializable {
    private static final long serialVersionUID = 113562799963510156L;

    @ApiModelProperty(value = "唯一标识",required = true)
    private String id;
    /**
    * 套餐名称
    */
    @ApiModelProperty(value = "套餐名称",required = true)
    private String name;
    /**
    * 套餐容量；Kb
    */
    @ApiModelProperty(value = "套餐容量",notes = "单位：Kb",required = true)
    private Double flux;
    /**
    * 套餐价格、分
    */
    @ApiModelProperty(value = "套餐价格",notes="单位：分",required = true)
    private Double price;
    /**
    * 有效期，天
    */
    @ApiModelProperty(value = "有效期",notes="单位：天",required = true)
    private BigInteger indate;
    /**
    * 套餐描述
    */
    @ApiModelProperty(value = "套餐描述",required = true)
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

    public BigInteger getIndate() {
        return indate;
    }

    public void setIndate(BigInteger indate) {
        this.indate = indate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}