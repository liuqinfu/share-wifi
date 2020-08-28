package com.aether.sharemainctlservice.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * (TShareLimit)实体类
 *
 * @author 我走路带风
 * @since 2020-08-28 16:30:53
 */
@Accessors(chain = true)
@Data
public class TShareLimit implements Serializable {
    private static final long serialVersionUID = -76147439704740045L;
    /**
    * 设备唯一标识
    */
    private String deviceId;
    
    private double limits;
    
    private Date createTime;
    
    private Date updateTime;

}