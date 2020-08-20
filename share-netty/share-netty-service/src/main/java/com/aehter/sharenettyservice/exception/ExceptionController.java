package com.aehter.sharenettyservice.exception;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharecommon.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @NAME: ExceptionController
 * @USER: 我走路带风
 * @DATETIME: 2020/5/12 13:32
 * @DESCRIPTION  全局异常处理
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResultVO<String> aPIExceptionHandler(APIException e) {
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVO<String> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<String> exceptionHandler(Exception e) {
        log.error(ExceptionUtil.getMessage(e));
        return new ResultVO<>(ResultCode.ERROR,e.getMessage());
    }

}
