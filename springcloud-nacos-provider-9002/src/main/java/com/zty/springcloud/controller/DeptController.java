package com.zty.springcloud.controller;


//提供Restful服务！

import com.zty.springcloud.pojo.Dept;
import com.zty.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //获取一些配置信息，得到具体的微服务！
    @Autowired
    private DiscoveryClient discoveryClient;


    @PostMapping("/dept/add")
    public boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        Dept dept= deptService.queryById(id);

        if (dept==null){
            throw new RuntimeException("这个id=>"+id+",不存在该用户，或信息无法找到~");
        }
        return  dept;
    }
    @GetMapping("/get/{id}")
    public String get1(@PathVariable("id") Long id){
        return  "数据库2中的数据";
    }
    @GetMapping("/dept/all")
    public List<Dept> queryAll( ){
        return deptService.queryAll();
    }

    //注册进来的微服务~  获取一些信息
    @GetMapping("/dept/discovery")
    public Object discovery(){
//        获取微服务列表的清单
        List<String> services = discoveryClient.getServices();
        System.out.println("discovery-->service"+services);
        //得到一个具体的微服务信息，通过具体的微服务id：Application名字
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-DEPT-8001");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"\t"+
                            instance.getPort()+"\t"+
                            instance.getUri()+"\t"+
                            instance.getServiceId()

            );
        }
        return this.discoveryClient;

    }




}
