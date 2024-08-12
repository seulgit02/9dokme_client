package com.example.server_9dokme.question.repository;

import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    //bookId, Chapter, bookPage로 조회 + 페이지네이션
    Page<Question> findByBook_BookIdAndChapterAndBookPage(Long bookId, String Chapter, int bookPage, Pageable pageable);
}
