package com.aether.sharemainctlservice.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author luojianye
 * @description 查询GPS信息 请求使用的VO
 * @date 2020/8/18 17:12
 */
@Data
public class GpsRequestVo implements Serializable {

    @NotBlank(message = "deviceId不能为空")
    private String deviceId;    // 设备唯一标识

    private String startDate;   // 开始日期【格式：yyyy-MM-dd】
    private String endDate;     // 结束日期【格式：yyyy-MM-dd】

//    @NotNull(message = "offset不能为空")
    private Integer offset;

//    @NotNull(message = "limit不能为空")
    private Integer limit;
}
