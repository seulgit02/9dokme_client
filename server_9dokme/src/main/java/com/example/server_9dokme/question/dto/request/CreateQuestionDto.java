package com.example.server_9dokme.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateQuestionDto {

    private String userEmail;

    private int bookChapter;

    private int bookPage;

    private String title;

    private String content;

}
