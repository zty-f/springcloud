package com.zty.springcloud.controller;

import com.zty.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private static  final String REST_URL_PREFIX="http://SPRINGCLOUD-PROVIDER-DEPT";
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping("/consumer/dept/all")
    public List<Dept> all(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/all",List.class);
    }

}
