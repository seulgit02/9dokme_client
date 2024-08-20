package com.example.server_9dokme.payment.service;

import com.example.server_9dokme.payment.dto.PaymentRequest;
import com.example.server_9dokme.payment.dto.PaymentResponse;

public interface PaymentInterface {
    PaymentResponse processPayment(PaymentRequest paymentRequest, String authKey);
}
