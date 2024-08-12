package com.example.server_9dokme.question.service;

import com.example.server_9dokme.book.dto.response.BookListDto;
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

@Slf4j
@Data
@NoArgsConstructor
@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CommentRepository commentRepository;

    public QuestionListDto getQuestionList(Long bookId, String chapter, int bookPage, int pageNo, String criteria){
        Pageable pageable = PageRequest.of(pageNo, 8, Sort.Direction.DESC, criteria);

        Page<Question> page = questionRepository.findByBook_BookIdAndChapterAndBookPage(bookId, chapter, bookPage, pageable);

        Page<QuestionDto> questionDtoPage = page.map(question -> new QuestionDto(
                question.getQuestionId(),
                question.getTitle(),
                question.getContent(),
                commentRepository.countByQuestion_QuestionId(question.getQuestionId()),
                question.getCreatedAt()
        ));
        return (QuestionListDto)questionDtoPage;

    }
}
