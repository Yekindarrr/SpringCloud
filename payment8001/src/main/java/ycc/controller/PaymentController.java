package ycc.controller;


import entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ycc.entities.CommonResult;
import ycc.service.PaymentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    //@RequestBody是作用在形参列表上，用于将前台发送过来固定格式的数据【xml格式 或者 json等】封装为对应的 JavaBean 对象，
    //封装时使用到的一个对象是系统默认配置的 HttpMessageConverter进行解析，然后封装到形参上。
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody entities.Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果:"+result);
        if(result>0){
            System.out.println("1112312323111");
            return new CommonResult(200,"插入数据库成功"+serverPort,result);
        }
        return new CommonResult(444,"插入数据库失败",null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:"+payment);
        if(payment!=null){
            System.out.println("2121212112");
            return new CommonResult(200,"查询成功"+serverPort,payment);
        }
        return new CommonResult(444,"查询失败",null);
    }
    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("element" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances(applicationName);
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;//返回服务接口
    }
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout()
    {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
