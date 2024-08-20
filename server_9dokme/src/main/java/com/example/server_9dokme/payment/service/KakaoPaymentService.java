package com.example.server_9dokme.payment.service;


import com.example.server_9dokme.payment.dto.PaymentRequest;
import com.example.server_9dokme.payment.dto.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class KakaoPaymentService implements PaymentInterface {

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest, String authKey) {
        return new PaymentResponse(
                paymentRequest.merchantUid(),
                paymentRequest.merchantUid(),
                paymentRequest.amount(),
                true
        );
    }
}