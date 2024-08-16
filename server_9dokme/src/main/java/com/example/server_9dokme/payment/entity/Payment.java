package com.example.server_9dokme.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String impUid;
    private String merchantUid;
    private int amount;
    private String buyerEmail;
    private String buyerName;
    private String buyerTel;
    private String status;  // 'READY', 'PAID', 'FAILED', etc.
}

