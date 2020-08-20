package com.aether.sharemainctlservice.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luojianye
 * @description 查询远程指令集 响应使用的VO
 * @date 2020/8/20 16:34
 */
@Data
public class TRemoteCmdResponseVo implements Serializable {

    private String deviceBand;      // 手机品牌
    private String sysVersion;      // 操作系统版本
    private String uiVersion;       // UI版本
    private String remoteCmds;      // 远程指令需要按执行顺序存储，结构为json

}
