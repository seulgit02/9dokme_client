package com.example.server_9dokme.question.controller;

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

    @GetMapping("/{bookId}/{chapter}/{bookPage}/{page}")
    public Page<QuestionDto> getQuestionList (
            @PathVariable Long bookId,
            @PathVariable String chapter,
            @PathVariable int bookPage,
            @PathVariable int page,
            @RequestParam(required = false, defaultValue = "lastViewedDate", value = "criteria") String criteria) {
        return questionService.getQuestionList(bookId, chapter, bookPage, page, criteria);
    }
}
