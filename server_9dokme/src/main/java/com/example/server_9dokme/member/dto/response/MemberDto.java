package com.example.server_9dokme.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MemberDto {
    private Long MemberId;
    private String nickname;
    private String socialId;
    private String expiredDate;
}
