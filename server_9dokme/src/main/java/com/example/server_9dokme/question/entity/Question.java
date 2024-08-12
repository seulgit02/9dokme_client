package com.example.server_9dokme.question.entity;

import com.example.server_9dokme.book.entity.Book;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionId;

    private String title;

    private String content;

    private String email;

    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    private String chapter;

    private String bookPage;

    @ManyToOne
    private Book book;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Comment> timetableList = new ArrayList<>();

}
