package com.aether.sharecommon.finals;

import lombok.Getter;

/**
 * @NAME: ResultCode
 * @USER: 我走路带风
 * @DATETIME: 2020/5/12 13:50
 * @DESCRIPTION  接口响应码
 **/
@Getter
public enum ResultCode {

    SUCCESS(true,200, "操作成功"),

    FAILED(false,201, "操作失败"),

    VALIDATE_FAILED(false,402, "参数校验失败"),

    ERROR(false,500, "未知错误");

    private boolean success;
    private int code;
    private String msg;

    ResultCode(boolean success,int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

}
