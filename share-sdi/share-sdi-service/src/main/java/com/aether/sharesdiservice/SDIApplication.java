package com.aether.sharesdiservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 我走路带风
 * @since 2020/8/12 13:40
 */
//@SpringBootApplication(exclude = {RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = {"com.aether.sharesdiservice.dao"})
public class SDIApplication {
    public static void main(String[] args) {
        SpringApplication.run(SDIApplication.class, args);
    }
}
