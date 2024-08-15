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

    Page<Book> findByTitleContaining(String Title,Pageable pageable);

    Page<Book> findAll(Pageable pageable);

    List<Book> findAll();

    Page<Book> findAllByCategory(String category, Pageable pageable);

    Boolean existsByTitle(String Title);

    Boolean existsByAuthor(String author);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByTitleContaining(String Title);

    Book findByBookId(Long bookId);


}
