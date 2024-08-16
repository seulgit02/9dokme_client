package com.example.server_9dokme.billing.entity;

import com.example.server_9dokme.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
//@EqualsAndHashCode(callSuper = false) // 부모 클래스의 equals, hashCode 호출하지 않음
public class Billing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billing_id")
    private Long id; // 내부 결제 시스템 식별자

    private Long memberId; // 결제 유저
    private String transactionId; // 외부 결제 시스템 식별자
    private BigDecimal amount;
    private String customerKey;
    private String authKey;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}