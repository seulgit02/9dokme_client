package com.example.server_9dokme.subscribe.entity;

import com.example.server_9dokme.common.entity.BaseEntity;
import com.example.server_9dokme.member.entity.Member;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Subscribe{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscribe_id")
    private Long subscribeId;

    private LocalDate createdAt;

    private LocalDate expiredAt;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private Boolean subscribeStatus;

    @OneToOne
    private Member  member;

}
