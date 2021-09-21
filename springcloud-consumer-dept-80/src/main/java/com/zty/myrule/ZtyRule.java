package com.zty.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZtyRule {

    //配置获取服务采用什么方式
    @Bean
    public IRule myRule(){
        return new RandomRule();//默认是轮询RandomRule,现在自定义为自己的
    }

}
