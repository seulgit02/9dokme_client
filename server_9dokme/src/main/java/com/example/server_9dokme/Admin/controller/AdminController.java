package com.example.server_9dokme.Admin.controller;

import com.example.server_9dokme.book.dto.request.BookCreateRequest;
import com.example.server_9dokme.book.dto.request.BookUpdateRequest;
import com.example.server_9dokme.book.dto.response.BookInfoResponse;
import com.example.server_9dokme.book.service.BookService;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final BookService bookService;

    // 책 정보(pdf) 등록
    @PostMapping("/books")
    public ResponseEntity<BookInfoResponse> createBook(@RequestBody BookCreateRequest request) {
        BookInfoResponse response = bookService.createBook(request);
        return ResponseEntity.ok(response);
    }

    // 책 정보(pdf) 수정
    @PutMapping("/books/{bookId}")
    public ResponseEntity<BookInfoResponse> updateBook(@PathVariable Long bookId, @RequestBody BookUpdateRequest request) {
        BookInfoResponse response = bookService.updateBook(bookId, request);
        return ResponseEntity.ok(response);
    }

    // 책 정보(pdf) 삭제
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

}
