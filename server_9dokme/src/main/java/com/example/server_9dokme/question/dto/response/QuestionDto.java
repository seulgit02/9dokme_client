package com.example.server_9dokme.question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class QuestionDto {
    private int questionId;
    private String title;
    private String content;
    private String chapter;
    private int bookPage;
    private int commentCount;
    private LocalDateTime createdAt;
}
