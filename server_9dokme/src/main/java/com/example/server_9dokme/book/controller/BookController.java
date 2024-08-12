package com.example.server_9dokme.book.controller;

import com.example.server_9dokme.book.dto.response.BookCheckDto;
import com.example.server_9dokme.book.dto.response.BookWebViewDto;
import com.example.server_9dokme.book.message.ErrorMessage;
import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.book.service.BookService;
import com.example.server_9dokme.common.dto.BaseResponse;
import com.example.server_9dokme.common.dto.ErrorResponse;
import com.example.server_9dokme.common.dto.SuccessResponse;
import com.example.server_9dokme.member.dto.response.BookDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseResponse getBookDetail(@RequestParam Long id) {

        if(bookRepository.existsById(id)==false){
            return ErrorResponse.of(ErrorMessage.INVALID_BOOK_ID.getMessage());
        }

        BookCheckDto dto = bookService.checkBook(id);

        return SuccessResponse.success("pdf 상세조회", dto);
    }

    @GetMapping("/bookPDF")
    @Operation(summary = "pdf 교재 검색", description = "pdf 교재 검색 title 기반")
    public BaseResponse searchBookPDF(@RequestParam String title,
                                      @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo) {

        Page<BookDto> dto = bookService.searchBook(title,pageNo);

        if(dto.isEmpty()){
            SuccessResponse.success("검색하신 교재는 존재하지 않습니다.");
        }

        return SuccessResponse.success("교재 검색", dto);
    }

    @GetMapping("/view")
    @Operation(summary = "pdf 교재 웹뷰 조회", description = "pdf 교재 웹뷰 조회")
    public BaseResponse viewBookPDF(@RequestParam Long bookId) {

        if(bookRepository.existsById(bookId)==false){
            return ErrorResponse.of("존재하지 않은 pdf교재입니다! 관리자에게 문의해주세요!");
        }

        BookWebViewDto dto = bookService.bookWebView(bookId);

        return SuccessResponse.success("pdf 교재", dto);
    }
}

