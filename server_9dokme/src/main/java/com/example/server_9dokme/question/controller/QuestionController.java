package com.example.server_9dokme.question.controller;

import com.example.server_9dokme.question.dto.request.CreateQuestionDto;
import com.example.server_9dokme.question.dto.request.QuesitonRequestDto;
import com.example.server_9dokme.question.dto.response.QuestionDetailDto;
import com.example.server_9dokme.question.dto.response.QuestionDto;
import com.example.server_9dokme.question.dto.response.QuestionListDto;
import com.example.server_9dokme.question.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/questionlist/{bookId}")
    public QuestionListDto getQuestionList (@PathVariable Long bookId,
                                            @RequestParam(required = false) String chapter,
                                            @RequestParam(required = false) Integer bookPage) {
        return questionService.getQuestionList(bookId, chapter, bookPage);
    }

    @GetMapping("/questiondetail/{questionId}")
    public QuestionDetailDto getQuestionList (@PathVariable int questionId ) {
        return questionService.getQuestionDetail(questionId);
    }
    @PostMapping("/community/question/{bookId}")
    @Operation(summary = "질문글 올리기", description = "양식에 맞춰서 chapter,bookPage(pdf)페이지")
    public ResponseEntity<String> createQuestion(@PathVariable("bookId") Long bookId,
                                                 @RequestBody CreateQuestionDto dto,
                                                 HttpSession session){

        Object currentMember = session.getAttribute("email");

        if (currentMember == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인을 진행해주세요!");
        }

        questionService.createQuestion(bookId, dto,currentMember);

        return ResponseEntity.ok("질문 업로드 성공");
    }
}
