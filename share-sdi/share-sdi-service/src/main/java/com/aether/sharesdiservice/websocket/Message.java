package com.aether.sharesdiservice.websocket;

import com.aether.sharecommon.utils.StringUtil;
import com.aether.sharesdiservice.enums.UsageMessageType;
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

    //发送时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String usageType = UsageMessageType.CHANGE_AP.getUsageType();

    public Message(Object message, UsageMessageType usageMessageType) {
        this.message = message;
        this.createTime = new Date();
        this.usageType = usageMessageType.getUsageType();
    }

    public Message(Object message) {
        this.message = message;
        this.createTime = new Date();
    }
}
