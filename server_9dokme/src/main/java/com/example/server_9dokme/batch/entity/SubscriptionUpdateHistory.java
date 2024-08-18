package com.example.server_9dokme.batch.entity;

import com.example.server_9dokme.member.entity.Member;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//결제 기록 DB
@Entity
@Data
@NoArgsConstructor
public class SubscriptionUpdateHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    private Long memberId;

    private LocalDateTime oldCreatedAt;

    private LocalDateTime newCreatedAt;

    private LocalDateTime oldExpiredAt;

    private LocalDateTime newExpiredAt;
}
