package com.aether.sharesdiservice.config;

import com.aether.sharesdiservice.intercepter.DataInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @NAME: InterceptorConfig
 * @USER: 我走路带风
 * @DATETIME: 2020/5/13 16:16
 * @DESCRIPTION
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private DataInterceptor dataInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 通过registry来注册拦截器，通过addPathPatterns来添加拦截路径
        registry.addInterceptor(this.dataInterceptor).addPathPatterns("/**");
    }
}
