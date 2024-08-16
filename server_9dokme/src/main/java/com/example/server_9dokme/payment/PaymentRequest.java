package com.example.server_9dokme.payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    private String pgProvider;
    private String payMethod;
    private String merchantUid;
    private String name;
    private int amount;
    private String buyerEmail;
    private String buyerName;
    private String buyerTel;
    private String buyerAddr;
    private String buyerPostcode;
}
