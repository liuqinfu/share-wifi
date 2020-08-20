package com.aehter.sharenettyservice.websocket;

import com.aehter.sharenettyservice.enums.MessageType;
import com.aehter.sharenettyservice.enums.UsageMessageType;
import com.aether.sharecommon.utils.StringUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class Message {

    /**
     * 消息id  该消息id唯一
     */
    private String msgId = StringUtil.get32GUID();

    //发送内容
    private Object message;

    //消息类型   request or  response
    private String messageType;

    //发送时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String usageType = UsageMessageType.CHANGE_AP.getUsageType();

    public Message(Object message, MessageType messageType, UsageMessageType usageMessageType) {
        this.message = message;
        this.createTime = new Date();
        this.messageType = messageType.getMessageType();
        this.usageType = usageMessageType.getUsageType();
    }

    public Message(Object message) {
        this.message = message;
        this.createTime = new Date();
    }
}
