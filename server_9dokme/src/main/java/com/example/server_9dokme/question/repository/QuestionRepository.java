package com.example.server_9dokme.question.repository;

import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.question.dto.response.QuestionDto;
import com.example.server_9dokme.question.dto.response.QuestionListDto;
import com.example.server_9dokme.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    //bookId, Chapter, bookPage로 조회 + 페이지네이션
    List<Question> findAllByBook_BookIdAndChapter(Long bookId, String chapter);
    List<Question> findAllByBook_BookIdAndBookPage(Long bookId, Integer bookPage);
    List<Question> findAllByBook_BookId(Long bookId);
    List<Question> findAllByBook_BookIdAndChapterAndBookPage(Long bookId, String chapter, Integer bookPage);

    Question findByQuestionId(int questionId);
    Question save(Question question);
}
