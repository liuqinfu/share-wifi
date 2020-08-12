package com.aether.sharesdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 我走路带风
 * @since 2020/8/12 13:40
 */
@SpringBootApplication
@EnableEurekaClient
public class SDIApplication {
    public static void main(String[] args) {
        SpringApplication.run(SDIApplication.class, args);
    }
}
