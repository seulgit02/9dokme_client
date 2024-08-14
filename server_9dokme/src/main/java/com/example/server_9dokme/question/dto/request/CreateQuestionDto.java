package com.example.server_9dokme.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateQuestionDto {

    private String userEmail;

    // [merge] chapter String으로 변경
    private String bookChapter;

    private int bookPage;

    private String title;

    private String content;

}
