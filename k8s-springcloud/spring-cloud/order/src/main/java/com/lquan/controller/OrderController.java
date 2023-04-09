package com.lquan.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * 测试订单的k8s项目
 */
@RestController
public class OrderController {


    @RequestMapping("/order")
    public String getUser(){


        return "hello  order test K8s";
    }

    @RequestMapping("/ip")
    public String getIpAddrStr(HttpServletRequest request){


        return this.getIpAddr(request);
    }

    @RequestMapping("/ip2")
    public String getIpAddrStr2(HttpServletRequest request){


        return this.getUrl();
    }

    @RequestMapping("/ip3")
    public String getIpAddrStr3(HttpServletRequest request){


        return this.getUrlx();
    }


    public String getUrlx() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "http://"+address.getHostAddress() ;
    }
    public static String getUrl(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String localAddr = request.getLocalAddr();
        int serverPort = request.getServerPort();
        return "http://"+localAddr +":"+ serverPort;
    }


    private String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /***
    @Autowired
    RestTemplate restTemplate;

    private static final String  POWER_URL="http://SERVER-POWER";
    private static final String  ORDER_URL="http://SERVER-ORDER";

    @Autowired
    private  PowerFeignClient powerFeignClient;


    @RequestMapping("/getUser.do")
    public R getUser(){
        Map<String,Object> map = new HashMap<>();
        map.put("key","user");

        return R.success("返回成功",map);
    }

    @RequestMapping("/getOrder.do")
    public R getOrder(){
        return R.success("操作成功",restTemplate.getForObject(ORDER_URL+"/getOrder.do",Object.class));
    }


    @RequestMapping("/getFeignPower.do")
    public R getFeignPower(){

        Object power = powerFeignClient.getPower();

        //  map.put("key","power1");

        //"msg":"power服务暂时不可用","code":"500"}

        powerFeignClient.getPowerList();

        return R.success("操作成功");

    }

    @RequestMapping("/getPower.do")
    @HystrixCommand(threadPoolKey = "power",
            threadPoolProperties ={@HystrixProperty(name = "coreSize",value = "5")
            })
    public R getPower(){
        System.out.println("调用了方法");
        return R.success("操作成功",restTemplate.getForObject(POWER_URL+"/getPower.do",Object.class));
    }


    public R  getFeignPowerFullBack(){
        return R.error("系统正在维护中,请稍后重试");
    }

    **/
}
