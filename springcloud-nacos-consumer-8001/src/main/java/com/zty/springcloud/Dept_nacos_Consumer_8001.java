package com.zty.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

//Ribbon和Eureka整合以后，客户端可以直接调用，不用关心ip地址和端口号

@SpringBootApplication
@EnableDiscoveryClient
//在微服务启动的时候就能去加载我们自定义的Ribbon类
public class Dept_nacos_Consumer_8001 {
    public static void main(String[] args) {
        SpringApplication.run(Dept_nacos_Consumer_8001.class,args);
    }

}
