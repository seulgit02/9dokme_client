package com.example.server_9dokme.payment.dto;

import com.example.server_9dokme.payment.entity.PaymentType;

public record PaymentRequest(
        String pgProvider,
        String payMethod,
        String merchantUid,
        String name,
        int amount,
        String buyerEmail,
        String buyerName,
        String buyerTel,
        String buyerAddr,
        String buyerPostcode,
        String authKey,
        PaymentType paymentType
) {}