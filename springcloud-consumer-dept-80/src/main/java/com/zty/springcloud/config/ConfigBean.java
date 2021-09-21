package com.zty.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  //相当于--spring的application.xml配置文件
public class ConfigBean {
    //配置负载均衡实现RestTemplate
    //IRule
    //RoundRobinRule  轮询算法
    //RandomRule 随机
    //AvailabilityFilteringRule  会过滤错误的服务，对剩下的服务进行轮询
    //ReTryRule   先按照轮询获取服务，若服务获取失败，则会在指定的时间内进行重试

    @Bean
    @LoadBalanced  //Ribbon
    public RestTemplate RestTemplate(){
        return new RestTemplate();
    }


}
