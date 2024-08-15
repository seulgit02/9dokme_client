package com.example.server_9dokme.question.service;

import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.member.repository.MemberRepository;
import com.example.server_9dokme.question.dto.request.CreateCommentDto;
import com.example.server_9dokme.question.dto.request.CreateQuestionDto;
import com.example.server_9dokme.question.entity.Comment;
import com.example.server_9dokme.question.entity.Question;
import com.example.server_9dokme.question.repository.CommentRepository;
import com.example.server_9dokme.question.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class QuestionService{

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void createQuestion(Long bookId, CreateQuestionDto dto, Object user){


        Question question = Question.builder()
                .book(bookRepository.findByBookId(bookId))
                .nickName(memberRepository.findBySocialId(user.toString()).getNickName())
                .chapter(dto.getBookChapter())
                .bookPage(dto.getBookPage())
                .title(dto.getTitle())
                .content(dto.getContent()).build();

        questionRepository.save(question);
    }


    @Transactional
    public void createComment(Integer questionId , Object user, CreateCommentDto dto){

        Comment comment = new Comment();

        // Optional<Question>을 처리합니다.
        Question question = questionRepository.findByQuestionId(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        comment.setQuestion(question);
        comment.setContent(dto.getContent());
        comment.setNickName(memberRepository.findBySocialId(user.toString()).getNickName());

        commentRepository.save(comment);
    }
}
