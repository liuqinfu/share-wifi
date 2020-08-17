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
    CHANGE_AP("changeap");

    private String usageType;

    public String getUsageType() {
        return usageType;
    }

    private UsageMessageType(String usageType){
        this.usageType = usageType;
    }

}
