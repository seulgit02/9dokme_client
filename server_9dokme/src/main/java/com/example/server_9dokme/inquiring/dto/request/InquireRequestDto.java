package com.example.server_9dokme.inquiring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InquireRequestDto {
    private String title;
    private String content;
//    private long userId;
}
