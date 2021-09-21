package com.zty.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  // 支持Nacos的动态刷新功能
public class ConfigClientController {
    @Value("${spring.application.name}")
    private String applicationName; //获取微服务名称

    @Value("${server.port}")
    private String port; //获取服务端的端口号

    @Value("${config.info}")
    private String configInfo; //获取配置中心一些信息

    @RequestMapping("/config")
    public String getConfig(){
        return "applicationName: "+applicationName + "-----" +
                "configInfo: "+configInfo +  "----" +
                "port: "+port;
    }
}
