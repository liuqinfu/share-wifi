package com.aether.sharemainctlservice.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luojianye
 * @description 远程指令集表（t_remotecmd_info）实体类
 * @date 2020/8/20 16:05
 */
@Data
public class TRemoteCmdInfo implements Serializable {

    private Integer id;             // 逻辑主键
    private String deviceBand;      // 手机品牌
    private String sysVersion;      // 操作系统版本
    private String uiVersion;       // UI版本
    private String remoteCmds;      // 远程指令需要按执行顺序存储，结构为json

}
