package ycc.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    /**
     * 正常访问，肯定OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池: "+Thread.currentThread().getName()+"    paymentInfo_OK,id: "+id+"\t"+"^_^";
    }

    public String paymentInfo_TimeOut(Integer id){

        int timeNumber = 3000;
        try {
            Thread.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: "+Thread.currentThread().getName()+"    paymentInfo_OK,id: "+id+"\t"+"^_^"+"  耗时(秒):"+timeNumber;
    }
}