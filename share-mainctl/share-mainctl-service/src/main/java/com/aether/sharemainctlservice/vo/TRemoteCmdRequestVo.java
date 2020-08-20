package com.aether.sharemainctlservice.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luojianye
 * @description 查询远程指令集 请求使用的VO
 * @date 2020/8/20 16:33
 */
@Data
public class TRemoteCmdRequestVo implements Serializable {

    private String deviceBand;      // 手机品牌
    private String sysVersion;      // 操作系统版本
    private String uiVersion;       // UI版本
    private Integer offset;         // 查询记录的起始位置
    private Integer limit;          // 查询的记录条数

}
