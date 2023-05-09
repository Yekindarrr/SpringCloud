package ycc.service.impl;


import entities.Payment;
import ycc.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ycc.service.PaymentService;

@Service
public class PaymentServiceImpl  implements PaymentService{
    @Autowired
    private PaymentDao paymentDao;
    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public entities.Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
}
