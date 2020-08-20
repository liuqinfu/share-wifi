package com.aether.sharecommon.finals;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @NAME: ResultVO
 * @USER: 我走路带风
 * @DATETIME: 2020/5/12 13:46
 * @DESCRIPTION  统一响应格式
 **/
@Getter
@ApiModel(value = "服务响应")
public class ResultVO<T> {

    /**
     * 业务处理是否成功
     */
    @ApiModelProperty(value = "是否成功",required = true)
    private boolean success;
    /**
     * 状态码，比如1000代表响应成功
     */
    @ApiModelProperty(value = "业务处理结果码",required = true)
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    @ApiModelProperty(value = "业务处理说明",required = false)
    private String msg;
    /**
     * 响应的具体数据
     */
    @ApiModelProperty(value = "响应数据",required = false)
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
                + "\"success\":"
                + success
                + ",\"code\":"
                + code
                + ",\"msg\":\""
                + msg + '\"'
                + ",\"data\":"
                + data
                + "}";

    }
}
