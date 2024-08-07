package com.example.server_9dokme.book.repository;

import com.example.server_9dokme.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByCategory(String category);
    Book findByTitle(String Title);

    List<Book> findAll();

    Book findByBookId(int bookId);
}
