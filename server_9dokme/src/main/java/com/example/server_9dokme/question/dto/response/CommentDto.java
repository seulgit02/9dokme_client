package com.example.server_9dokme.question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CommentDto {
    int commentId;
    String content;
    private LocalDateTime createdAt;
}
