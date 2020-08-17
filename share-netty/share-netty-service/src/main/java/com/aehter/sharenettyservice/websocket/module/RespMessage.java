package com.aehter.sharenettyservice.websocket.module;

import lombok.Data;

@Data
public class RespMessage {

    private String msgId;

    private int code;

    private String msg;
}
