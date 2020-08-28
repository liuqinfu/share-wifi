package com.aether.sharemainctlservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * (TFluxMeal)实体类
 *
 * @author 我走路带风
 * @since 2020-08-20 17:32:28
 */
@ApiModel(value = "已购流量套餐详情")
@Accessors(chain = true)
@Data
public class TFluxMeal implements Serializable {
    private static final long serialVersionUID = -85433710784196185L;

    @ApiModelProperty(value = "记录id",required = true)
    private String id;
    /**
    * 设备唯一标识
    */
    @ApiModelProperty(value = "设备唯一标识",required = true)
    private String deviceId;
    /**
    * 订单id
    */
    @ApiModelProperty(value = "订单号",required = true)
    private String orderId;
    /**
    * 已使用流量 Kb
    */
    @ApiModelProperty(value = "已使用流量",notes = "单位：Kb",required = true)
    private double usedFlux;
    /**
    * 剩余流量  Kb
    */
    @ApiModelProperty(value = "剩余流量",notes = "单位：Kb",required = true)
    private double leftFlux;
    /**
    * 套餐状态  1：使用中、2：使用完、3：已失效
    */
    @ApiModelProperty(value = "套餐状态",allowableValues = "1,2,3",required = true)
    private Integer status;
    /**
    * 生效时间
    */
    @ApiModelProperty(value = "生效时间",required = true)
    private ZonedDateTime startTime;
    /**
    * 失效时间
    */
    @ApiModelProperty(value = "失效时间",required = true)
    private ZonedDateTime invildTime;

    @ApiModelProperty(hidden = true)
    private ZonedDateTime createTime;

    @ApiModelProperty(value = "激活时间",required = true)
    private ZonedDateTime updateTime;


}