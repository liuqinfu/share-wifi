package com.aether.sharemainctlservice.exception;

import com.aether.sharecommon.finals.ResultCode;
import lombok.Getter;

/**
 * @author luojianye
 * @description 用于参数校验失败抛出的异常
 * @date 2020/8/19 9:30
 */
@Getter
public class ParameterException extends RuntimeException {

    private int code;
    private String message;

    public ParameterException() {
        super();
        this.code = ResultCode.VALIDATE_FAILED.getCode();
        this.message = ResultCode.VALIDATE_FAILED.getMsg();
    }

    public ParameterException(String message) {
        super(message);
        this.code = ResultCode.VALIDATE_FAILED.getCode();
        this.message = message;
    }

    public ParameterException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResultCode.VALIDATE_FAILED.getCode();
        this.message = message;
    }

    public ParameterException(Throwable cause) {
        super(cause);
        this.code = ResultCode.VALIDATE_FAILED.getCode();
        this.message = ResultCode.VALIDATE_FAILED.getMsg();
    }
}
