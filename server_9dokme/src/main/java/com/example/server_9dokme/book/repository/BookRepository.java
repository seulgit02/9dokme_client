package com.example.server_9dokme.book.repository;

import com.example.server_9dokme.book.entity.Book;

import com.example.server_9dokme.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByCategory(String category);
    Book findByTitle(String Title);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findAllByCategory(String category, Pageable pageable);


    Book findByBookId(int bookId);

    Page<Book> findAllByMember(Member member, Pageable pageable);
}
