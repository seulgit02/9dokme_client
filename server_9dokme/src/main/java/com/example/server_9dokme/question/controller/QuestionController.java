package com.example.server_9dokme.question.controller;

import com.example.server_9dokme.question.dto.request.CreateQuestionDto;
import com.example.server_9dokme.question.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@Data
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    QuestionService questionService;

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
