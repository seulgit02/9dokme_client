package com.example.server_9dokme.question.service;

import com.example.server_9dokme.book.dto.response.BookListDto;
import com.example.server_9dokme.question.dto.request.QuesitonRequestDto;
import com.example.server_9dokme.question.dto.response.QuestionDto;
import com.example.server_9dokme.question.dto.response.QuestionListDto;
import com.example.server_9dokme.question.entity.Question;
import com.example.server_9dokme.question.repository.CommentRepository;
import com.example.server_9dokme.question.repository.QuestionRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CommentRepository commentRepository;

    public QuestionListDto getQuestionList(Long bookId){
        List<Question> questions = questionRepository.findAllByBook_BookId(bookId);

        List<QuestionDto> questionDtoList = questions.stream()
                .map(question -> QuestionDto.builder()
                        .questionId(question.getQuestionId())
                        .title(question.getTitle())
                        .content(question.getContent())
                        .commentCount(commentRepository.countByQuestion_QuestionId(question.getQuestionId()))
                        .createdAt(question.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return QuestionListDto.builder()
                .questionList(questionDtoList)
                .build();
    }
}
