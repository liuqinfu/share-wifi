package com.aether.sharemainctlservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * (TOrderInfo)实体类
 *
 * @author 我走路带风
 * @since 2020-08-20 17:33:49
 */
@Accessors(chain = true)
@Data
@ApiModel(value = "流量套餐订单",description = "用户的流量套餐订单")
public class TOrderInfo implements Serializable {
    private static final long serialVersionUID = -58285142698752322L;

    @ApiModelProperty(value = "订单号",required = true)
    private String id;
    /**
    * 设备id
    */
    @ApiModelProperty(value = "设备标识",required = true)
    private String deviceId;
    /**
    * 套餐id
    */
    @ApiModelProperty(value = "套餐id",required = true)
    private String mealId;
    /**
    * 支付价格
    */
    @ApiModelProperty(value = "支付价格",required = true)
    private Double payPrice;
    /**
    * 订单状态  1、已创建、待支付；2、已创建、已支付；3、已创建、已取消
    */
    @ApiModelProperty(value = "订单状态",allowableValues = "1,2,3",required = true)
    private Integer status;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间",required = true)
    private Date createTime;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "状态更新时间",required = true)
    private Date updateTime;


}