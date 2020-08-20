package com.aehter.sharenettyservice.enums;

public enum MessageType {

    /**
     * 请求
     */
    REQUEST("request"),

    /**
     * 响应
     */
    RESPONSE("response");


    private String messageType;

    public String getMessageType() {
        return messageType;
    }

    private MessageType(String messageType){
        this.messageType = messageType;
    }

}
