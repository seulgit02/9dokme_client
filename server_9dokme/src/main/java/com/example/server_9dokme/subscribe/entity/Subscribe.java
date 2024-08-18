package com.example.server_9dokme.subscribe.entity;

import com.example.server_9dokme.common.entity.BaseEntity;
import com.example.server_9dokme.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subscribe extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscribe_id")
    private Long subscribeId;

//    private Boolean status;

    //결제 시작일
    private LocalDateTime createdAt;

    //결제 만료일 (createdAt+30일)
    private LocalDateTime expiredAt;

    private enum paymentStatus{};

    private Boolean subscribeStatus;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
