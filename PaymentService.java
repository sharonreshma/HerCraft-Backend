// PaymentService.java
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        try {
            return paymentRepository.save(payment);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save payment data", e);
        }
    }

    
}
