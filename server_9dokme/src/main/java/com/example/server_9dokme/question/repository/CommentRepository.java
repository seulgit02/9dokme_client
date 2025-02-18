package com.example.server_9dokme.question.repository;

import com.example.server_9dokme.question.entity.Comment;
import com.example.server_9dokme.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    //commentcount 리턴
    int countByQuestion_QuestionId(int questionId);
    List<Comment> findAllByQuestion_QuestionId(int questionId);

    Boolean existsByQuestionAndCommentId(Question question, Long commentId);

    void deleteCommentByQuestion_QuestionIdAndCommentId(Integer question, Integer commentId);
}
