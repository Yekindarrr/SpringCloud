package ycc.controller;

import entities.CommonResult;
import entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ycc.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class FeignController {
    @Resource
    private PaymentService paymentService;
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentService.getPaymentById(id);
    }
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon，客户端一般默认等待1秒钟
        return paymentService.paymentFeignTimeout();
    }
}
