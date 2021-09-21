package com.zty.springcloud.controller;


//提供Restful服务！

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zty.springcloud.myhandler.CustomerBlockHandler;
import com.zty.springcloud.pojo.Dept;
import com.zty.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @GetMapping("/dept/all")
    public List<Dept> queryAll( ){
        //线程需要花费的时间
        //try {
        //    TimeUnit.MILLISECONDS.sleep(2000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
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

    @GetMapping("/testD")
    @SentinelResource(value = "TD", blockHandlerClass = CustomerBlockHandler.class,
                        blockHandler = "handlerException")
    public String testD(){
        //暂停几秒线程
        //try {
        //    TimeUnit.SECONDS.sleep(1);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //int a=10/0;
        System.out.println("testD 测试RT");
        return "------testD";
    }

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey",blockHandler = "deal_testHotkey")
    public String testHotkey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        //int a=10/0;
        return "------->Hotkey测试";
    }
    public String deal_testHotkey(String p1, String p2, BlockException exception){
        return "--------deal_testHotkey,--___---"+p1+"----"+p2;
    }


}
