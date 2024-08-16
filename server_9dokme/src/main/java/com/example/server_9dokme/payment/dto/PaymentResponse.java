package com.example.server_9dokme.payment.dto;

public record PaymentResponse(
        String customerUid,
        String merchantUid,
        int paidAmount,
        boolean success
) {}
