package com.zty.springcloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//启动类
@SpringBootApplication
@EnableDiscoveryClient  //服务可以被发现

public class NacosProvider9002 {
    public static void main(String[] args) {
        SpringApplication.run(NacosProvider9002.class,args);
    }

}
