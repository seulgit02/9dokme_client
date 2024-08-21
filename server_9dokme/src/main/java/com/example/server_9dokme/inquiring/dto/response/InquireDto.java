package com.example.server_9dokme.inquiring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InquireDto {
    private Long inquireId;
    private Long userId;
    private String title;
    private String content;
}
