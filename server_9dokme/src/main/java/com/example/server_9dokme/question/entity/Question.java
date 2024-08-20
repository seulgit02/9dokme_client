package com.example.server_9dokme.question.entity;

import com.example.server_9dokme.book.entity.Book;
import jakarta.persistence.*;
import lombok.*;
import com.example.server_9dokme.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionId;

    private String title;

    private String content;

    private String nickName;

    private int chapter;

    private int bookPage;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Comment> timetableList = new ArrayList<>();

}
