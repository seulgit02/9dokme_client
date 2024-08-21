package com.example.server_9dokme.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProfileDto {
    private Long memberId;
    private String nickName;
    private String expirationDate;
//    private String profileImageURL;
}
