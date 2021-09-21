package com.zty.springcloud.service;

import org.springframework.stereotype.Component;

import java.util.List;


//服务降级
@Component
public class DeptClientServiceFallBackFactory implements DeptClientService {

            @Override
            public String queryById(Long id) {
                return "没有对应的信息，客户端提供了降级的信息，这个服务现在已经被关闭";

            }


};


