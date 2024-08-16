package com.example.server_9dokme.payment.entity;

import com.example.server_9dokme.common.entity.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

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
    private String impUid;

    private String status;
    private int retryCount; // 새로운 필드 추가
}

