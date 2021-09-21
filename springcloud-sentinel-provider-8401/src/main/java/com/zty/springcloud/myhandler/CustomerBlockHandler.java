/**
 * @author: zty
 * @program: springcloud
 * @ClassName CustomerBlockHandler
 * @description:
 * @create: 2021-09-20 16:36
 * @Version 1.0
 **/
package com.zty.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {
    public static String handlerException(BlockException e){
        return "自定义的限流反馈信息方法！";
    }
}
