package com.example.server_9dokme.book.controller;

import com.example.server_9dokme.book.dto.response.BookCheckDto;
import com.example.server_9dokme.book.dto.response.BookWebViewDto;
import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.message.ErrorMessage;
import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.book.service.BookService;
import com.example.server_9dokme.common.dto.BaseResponse;
import com.example.server_9dokme.common.dto.ErrorResponse;
import com.example.server_9dokme.common.dto.SuccessResponse;
import com.example.server_9dokme.member.dto.response.BookDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/bookdetail")
    @Operation(summary = "pdf 교재 상세조회", description = "pdf 교재 상세조회")
    public ResponseEntity<BookCheckDto> getBookDetail(@RequestParam Long id,
                                                      HttpSession session) {

        Object currentMember = session.getAttribute("email");

        if (currentMember == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인 후 이용해주세요.");
        }
        if(bookRepository.existsById(id)==false){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없는 pdf 교재입니다.");
        }

        BookCheckDto dto = bookService.checkBook(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/bookPDF")
    @Operation(summary = "pdf 교재 검색", description = "pdf 교재 검색 title 기반")
    public ResponseEntity<Page<BookDto>> searchBookPDF(@RequestParam String title,
                                      @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
                                                       HttpSession session) {

        Page<BookDto> dto = bookService.searchBook(title,pageNo);

        Object currentMember = session.getAttribute("email");

        if (currentMember == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인 후 이용해주세요.");
        }
        if(dto.isEmpty()==false){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없는 pdf 교재입니다.");
        }



        return ResponseEntity.ok(dto);
    }

    @GetMapping("/view")
    @Operation(summary = "pdf 교재 웹뷰 조회", description = "pdf 교재 웹뷰 조회")
    public ResponseEntity<BookWebViewDto> viewBookPDF(@RequestParam Long bookId,
                                                      HttpSession session) {

        Object currentMember = session.getAttribute("email");

        if (currentMember == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인 후 이용해주세요.");
        }

        if(bookRepository.existsById(bookId)==false){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없는 pdf 교재입니다.");
        }

        BookWebViewDto dto = bookService.bookWebView(bookId);

        return ResponseEntity.ok(dto);
    }
}

