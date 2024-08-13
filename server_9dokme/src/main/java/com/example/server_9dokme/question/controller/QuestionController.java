package com.example.server_9dokme.question.controller;

import com.example.server_9dokme.question.dto.request.QuesitonRequestDto;
import com.example.server_9dokme.question.dto.response.QuestionDto;
import com.example.server_9dokme.question.dto.response.QuestionListDto;
import com.example.server_9dokme.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/questionlist/{bookId}/{page}")
    public QuestionListDto getQuestionList (@PathVariable Long bookId ) {
        return questionService.getQuestionList(bookId);
    }
}
