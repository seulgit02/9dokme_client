package com.example.server_9dokme.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class QuesitonRequestDto {
    private Long bookId;
    private String chapter;
    private int bookPage;
    private int pageNo;
}
