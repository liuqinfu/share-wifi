package com.aether.sharemainctlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 我走路带风
 * @since 2020/8/13 10:11
 */
//@SpringBootApplication(exclude = {RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
@SpringBootApplication
@EnableEurekaClient
public class MainctlApplication {
    public static void main(String[] args) {

        SpringApplication.run(MainctlApplication.class,args);

    }
}
