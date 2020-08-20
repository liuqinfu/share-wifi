package com.aehter.sharenettyservice.intercepter;

import com.aether.sharecommon.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @NAME: DataInterceptor
 * @USER: 我走路带风
 * @DATETIME: 2020/5/13 15:43
 * @DESCRIPTION  拦截入参以及输出
 **/
@Slf4j
@Component
public class DataInterceptor implements HandlerInterceptor {
    //请求处理前，也就是访问Controller前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request	"+request.getMethod()+" "+request.getRequestURI()+"--------IP:" + HttpUtil.getIP(request) + "---------params:" + HttpUtil.dumpHttpRequest(request));
        return true;
    }
    //请求已经被处理，但在视图渲染前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    //请求结果结果已经渲染好了，response没有进行返回但也无法修改reponse了（一般用于清理数据）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
