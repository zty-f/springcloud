package com.zty.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zty.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {
    //消费者不应该有Service层
    //RestTemplate    有很多方法供我们直接调用就可以了！注册到Spring中
    //(url,实体：Map,Class<T>,responseType)
    @Autowired
    private RestTemplate restTemplate;
    //restTemple提供多种便捷访问远程Http服务的方法，简单的RestFul服务模板！
    //private static  final String REST_URL_PREFIX="http://localhost:8001";

    //Ribbon   我们这里的地址应该是一个变量，通过服务名来访问
    @Value("${service-url.nacos-user-service}")
    private String REST_URL_PREFIX;

    //@RequestMapping("/consumer/dept/get/{id}")
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")
    //public Dept get(@PathVariable("id") Long id){
    //    //System.out.println(REST_URL_PREFIX);
    //    if (id==4){
    //        throw new IllegalArgumentException("非法参数异常！");
    //    }else if (id==5){
    //        throw new NullPointerException("空指针异常！");
    //    }
    //    return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    //}
     @RequestMapping("/consumer/dept/get/{id}")
     //@SentinelResource(value = "fallback") //没有配置
    //@SentinelResource(value = "fallback", fallback = "handlerFallback") //只配置了fallback负责业务异常
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler") //只配置了blockHandler负责sentinel控制台配置违规
    @SentinelResource(value = "fallback",
            blockHandler = "blockHandler",
            fallback = "handlerFallback",
            exceptionsToIgnore = {IllegalArgumentException.class}) //只配置了blockHandler负责sentinel控制台配置违规
    public String get(@PathVariable("id") Long id){
        //System.out.println(REST_URL_PREFIX);
        if (id==4){
            throw new IllegalArgumentException("非法参数异常！");
        }else if (id==5){
            throw new NullPointerException("空指针异常！");
        }
        return restTemplate.getForObject(REST_URL_PREFIX+"/get/"+id, String.class);
    }

    public String handlerFallback(@PathVariable("id") Long id, Throwable e){
        //System.out.println(id+e.getMessage());
        return id+e.getMessage();
    }
    public String blockHandler(@PathVariable("id") Long id, BlockException e){
        //System.out.println(id+e.getMessage());
        return id+e.getMessage();
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        //System.out.println(REST_URL_PREFIX);
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping("/consumer/dept/all")
    public List<Dept> all(){
        //System.out.println(REST_URL_PREFIX);
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/all",List.class);
    }

}
