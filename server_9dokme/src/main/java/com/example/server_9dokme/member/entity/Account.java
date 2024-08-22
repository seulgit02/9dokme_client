package com.example.server_9dokme.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private Long id;
    private String email;
    private String kakaoName;
}
