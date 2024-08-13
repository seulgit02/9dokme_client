package com.example.server_9dokme.book.repository;

import com.example.server_9dokme.book.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByCategory(String category);
    Page<Book> findByTitleContaining(String Title,Pageable pageable);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findAllByCategory(String category, Pageable pageable);


    Book findByBookId(Long bookId);

}
