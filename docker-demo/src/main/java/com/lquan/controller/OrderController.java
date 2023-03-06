package com.lquan.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @program: k8s
 * @description:
 * @author: lquan
 * @create: 2023-02-27 12:08
 **/
@RestController
public class OrderController {

    @Value( "${server.port}" )
    private String port ;

    @RequestMapping( "/order/docker" )
    public String helloDocker(){
        return "hello docker" + " \t " + port + " \t " + UUID.randomUUID().toString();
    }

    @RequestMapping (value = "/order/index" ,method = RequestMethod.GET )
    public String index() {
        return " 服务端口号 : " + " \t " + port + " \t " +UUID.randomUUID().toString();
    }

    @RequestMapping (value = "/" ,method = RequestMethod.GET )
    public String getPort() {
        return " 服务端口号 : " + " \t " + port + " \t " +UUID.randomUUID().toString()+"其他端口url地址：/order/docker     /order/index";
    }
}
