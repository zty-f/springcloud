package com.zty.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Ribbon和Eureka整合以后，客户端可以直接调用，不用关心ip地址和端口号

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.zty.springcloud"})
public class DeptConsumer_feign_84 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_feign_84.class,args);
    }

}
