package com.aehter.sharenettyservice.enums;

public enum UsageMessageType {

    /**
     * 远程指令集
     */
    REMOTE_CMDS("remotecmds"),

    /**
     * 指令下发
     */
    OPERATION("operation"),

    /**
     * 切换ap
     */
    CHANGE_AP("changeap"),

    /**
     * 位置上报
     */
    REPORT_GPS("gps"),

    /**
     * 流量上报
     */
    REPORT_FLUX("flux"),


    /**
     * 心跳
     */
    HEART_BREAK("heartbreak");

    private String usageType;

    public String getUsageType() {
        return usageType;
    }

    private UsageMessageType(String usageType){
        this.usageType = usageType;
    }

}
