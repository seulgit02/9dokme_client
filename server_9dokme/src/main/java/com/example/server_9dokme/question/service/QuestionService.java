package com.example.server_9dokme.question.service;

import com.example.server_9dokme.book.dto.response.BookListDto;
import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.member.repository.MemberRepository;
import com.example.server_9dokme.question.dto.request.CreateCommentDto;
import com.example.server_9dokme.question.dto.request.CreateQuestionDto;
import com.example.server_9dokme.question.dto.response.CommentDto;
import com.example.server_9dokme.question.dto.response.QuestionDetailDto;
import com.example.server_9dokme.question.dto.response.QuestionDto;
import com.example.server_9dokme.question.dto.response.QuestionListDto;
import com.example.server_9dokme.question.entity.Comment;
import com.example.server_9dokme.question.entity.Question;
import com.example.server_9dokme.question.repository.CommentRepository;
import com.example.server_9dokme.question.repository.QuestionRepository;
import jakarta.transaction.Transactional;
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
public class QuestionService{

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BookRepository bookRepository;

    public QuestionListDto getQuestionList(Long bookId, Integer chapter, Integer bookPage){
        List<Question> questions;

        if (chapter != null && bookPage != null) {
            questions = questionRepository.findAllByBook_BookIdAndChapterAndBookPage(bookId, chapter, bookPage);
        } else if (chapter != null) {
            questions = questionRepository.findAllByBook_BookIdAndChapter(bookId, chapter);
        } else if (bookPage != null) {
            questions = questionRepository.findAllByBook_BookIdAndBookPage(bookId, bookPage);
        } else {
            questions = questionRepository.findAllByBook_BookId(bookId);
        }

        List<QuestionDto> questionDtoList = questions.stream()
                .map(question -> QuestionDto.builder()
                        .questionId(question.getQuestionId())
                        .title(question.getTitle())
                        .content(question.getContent())
                        .chapter(question.getChapter())
                        .bookPage(question.getBookPage())
                        .commentCount(commentRepository.countByQuestion_QuestionId(question.getQuestionId()))
                        .createdAt(question.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return QuestionListDto.builder()
                .questionList(questionDtoList)
                .build();
    }

    public QuestionDetailDto getQuestionDetail(int questionId){
        Question question = questionRepository.findByQuestionId(questionId);

        if (question == null) {
            throw new RuntimeException("Question not found with ID: " + questionId);
        }

        QuestionDto questionDto = QuestionDto.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .content(question.getContent())
                .commentCount(commentRepository.countByQuestion_QuestionId(question.getQuestionId()))
                .createdAt(question.getCreatedAt())
                .build();

        List<Comment> commentList = commentRepository.findAllByQuestion_QuestionId(questionId);

        List<CommentDto> commentDtoList = commentList.stream()
                .map(comment -> CommentDto.builder()
                        .commentId(comment.getCommentId())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .nickName(comment.getNickName())
                        .memberId(memberRepository.findBySocialId(comment.getEmail()).getMemberId())
                        .build())
                .collect(Collectors.toList());

        return new QuestionDetailDto(questionDto, commentDtoList);
    }

    @Transactional
    public void createQuestion(Long bookId, CreateQuestionDto dto, Object user){


        Question question = Question.builder()
                .book(bookRepository.findByBookId(bookId))
                .nickName(memberRepository.findBySocialId(user.toString()).getNickName())
                .email(user.toString())
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
        comment.setEmail(user.toString());

        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Integer questionId, Integer commentId){

        commentRepository.deleteCommentByQuestion_QuestionIdAndCommentId(questionId,commentId);
    }
}
