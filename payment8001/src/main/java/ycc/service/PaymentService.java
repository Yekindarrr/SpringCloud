package ycc.service;

import entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment);
    public entities.Payment getPaymentById(@Param("id") Long id);
}
