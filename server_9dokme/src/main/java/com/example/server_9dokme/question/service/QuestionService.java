package com.example.server_9dokme.question.service;

import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.question.dto.request.CreateQuestionDto;
import com.example.server_9dokme.question.entity.Question;
import com.example.server_9dokme.question.repository.QuestionRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class QuestionService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    QuestionRepository questionRepository;

    public void createQuestion(Long bookId, CreateQuestionDto dto, Object user){


        Question question = Question.builder()
                .book(bookRepository.findByBookId(bookId))
                .email(user.toString())
                .chapter(dto.getBookChapter())
                .bookPage(dto.getBookPage())
                .title(dto.getTitle())
                .content(dto.getContent()).build();

        questionRepository.save(question);
    }
}
