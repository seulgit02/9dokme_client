package com.example.server_9dokme.book.service;

import com.example.server_9dokme.book.dto.request.BookCreateRequest;
import com.example.server_9dokme.book.dto.request.BookUpdateRequest;
import com.example.server_9dokme.book.dto.response.BookInfoResponse;
import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.exception.BookException;
import com.example.server_9dokme.book.message.ErrorMessage;
import com.example.server_9dokme.book.repository.BookRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Optional<Book> findById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    @Transactional
    public BookInfoResponse createBook(BookCreateRequest request) {
        Book book = Book.create(
                request.title(),
                request.publishDate(),
                request.author(),
                request.publisher(),
                request.category(),
                request.description(),
                request.bookImage(),
                request.bookURL(),
                request.bookChapter(),
                request.bookFullPage(),
                request.rent()
        );
        bookRepository.save(book);
        return toResponse(book);
    }

    @Transactional
    public BookInfoResponse updateBook(Long bookId, BookUpdateRequest request) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException(ErrorMessage.NOT_FOUND_BOOK));

        book.update(
                request.title(),
                request.publishDate(),
                request.author(),
                request.publisher(),
                request.category(),
                request.description(),
                request.bookImage(),
                request.bookURL(),
                request.bookChapter(),
                request.bookFullPage(),
                request.rent()
        );

        bookRepository.save(book);
        return toResponse(book);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException(ErrorMessage.NOT_FOUND_BOOK));
        bookRepository.delete(book);
    }

    private BookInfoResponse toResponse(Book book) {
        return new BookInfoResponse(
                book.getBookId(),
                book.getTitle(),
                book.getPublishDate(),
                book.getAuthor(),
                book.getPublisher(),
                book.getCategory(),
                book.getDescription(),
                book.getBookImage(),
                book.getBookURL(),
                book.getBookChapter(),
                book.getBookFullPage(),
                book.getRent()
        );
    }
}
