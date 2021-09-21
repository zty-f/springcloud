package com.zty.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClient_3377 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient_3377.class,args);
    }
}
