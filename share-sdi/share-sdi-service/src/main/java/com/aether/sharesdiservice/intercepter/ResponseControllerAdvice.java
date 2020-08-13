package com.aether.sharesdiservice.intercepter;

import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharesdiservice.exception.APIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @NAME: ResponseControllerAdvice
 * @USER: 我走路带风
 * @DATETIME: 2020/5/12 13:57
 * @DESCRIPTION  省去接口层面的对返回数据的包装
 **/
@Slf4j
@RestControllerAdvice(basePackages = {"com.aether.sharesdi.controller"}) // 注意哦，这里要加上需要扫描的包
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {



    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
//        return !returnType.getParameterType().equals(ResultVO.class);
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            // String类型不能直接包装，所以要进行些特别的处理
            if (returnType.getGenericParameterType().equals(String.class)) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
                    data = new ResultVO<>(data);
                    return objectMapper.writeValueAsString(data);
                } catch (JsonProcessingException e) {
                    throw new APIException("返回String类型错误");
                }
            }else if (!returnType.getParameterType().equals(ResultVO.class)){
                // 将原本的数据包装在ResultVO里
                data = new ResultVO<>(data);
            }
        } finally {
            log.info("response  {}--------IP: {}---------response: {}", request.getMethodValue()+"\t"+request.getURI().getPath(), request.getRemoteAddress().getHostString(), data);
        }
        return data;
    }
}