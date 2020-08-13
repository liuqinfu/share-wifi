package com.aether.sharecommon.finals;

import lombok.Getter;

/**
 * @NAME: ResultVO
 * @USER: 我走路带风
 * @DATETIME: 2020/5/12 13:46
 * @DESCRIPTION  统一响应格式
 **/
@Getter
public class ResultVO<T> {

    /**
     * 业务处理是否成功
     */
    private boolean success;
    /**
     * 状态码，比如1000代表响应成功
     */
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    /**
     * 成功
     * @param data
     */
    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.success = resultCode.isSuccess();
        this.data = data;
    }

    public ResultVO(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.success = resultCode.isSuccess();
    }

    public ResultVO(boolean success,int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    @Override
    public String toString() {
        return "{"
                + "\"code\":"
                + code
                + ",\"msg\":\""
                + msg + '\"'
                + ",\"data\":"
                + data
                + "}";

    }
}
