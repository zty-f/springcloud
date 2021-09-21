package com.zty.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

//Ribbon和Eureka整合以后，客户端可以直接调用，不用关心ip地址和端口号

@SpringBootApplication
@EnableEurekaClient  //客户端，可以请求服务
@EnableFeignClients(basePackages = {"com.zty.springcloud"})
public class DeptConsumer_feign {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_feign.class,args);
    }

}
