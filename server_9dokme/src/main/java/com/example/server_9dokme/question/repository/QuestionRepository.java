package com.example.server_9dokme.question.repository;

import com.example.server_9dokme.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {


    Optional<Question> findByQuestionId(Integer id);

    Question save(Question question);
}
