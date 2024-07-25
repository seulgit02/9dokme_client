package com.example.server_9dokme.book.repository;

import com.example.server_9dokme.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
