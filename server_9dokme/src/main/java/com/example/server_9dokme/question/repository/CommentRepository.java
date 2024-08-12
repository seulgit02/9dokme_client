package com.example.server_9dokme.question.repository;

import com.example.server_9dokme.question.entity.Comment;
import com.example.server_9dokme.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //commentcount 리턴
    int countByQuestion_QuestionId(int questionId);
}
