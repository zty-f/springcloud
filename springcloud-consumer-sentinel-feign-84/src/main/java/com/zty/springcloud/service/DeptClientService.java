package com.zty.springcloud.service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Service
@FeignClient(value = "nacos-provider-dept",fallback= DeptClientServiceFallBackFactory.class)
public interface DeptClientService {


    @GetMapping("/get/{id}")
    public String queryById(@PathVariable("id") Long id);



}
