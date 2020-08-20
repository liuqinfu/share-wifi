package com.aether.sharemainctlservice;

import com.thebeastshop.forest.springboot.annotation.ForestScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 我走路带风
 * @since 2020/8/13 10:11
 */
//@SpringBootApplication(exclude = {RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.aether.sharemainctlservice.dao"})
@ForestScan(basePackages = {"com.aether.sharemainctlservice.thirdparty"})
public class MainctlApplication {
    public static void main(String[] args) {

        SpringApplication.run(MainctlApplication.class,args);

    }
}
