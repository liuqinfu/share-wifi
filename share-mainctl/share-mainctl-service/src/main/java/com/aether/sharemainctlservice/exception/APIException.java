package com.aether.sharemainctlservice.exception;

import lombok.Getter;

/**
 * @NAME: APIException
 * @USER: 我走路带风
 * @DATETIME: 2020/5/12 13:37
 * @DESCRIPTION
 **/
@Getter //只要getter方法，无需setter
public class APIException extends RuntimeException {
    private int code;
    private String msg;

    public APIException() {
        this(500, "接口错误");
    }

    public APIException(String msg) {
        this(500, msg);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
