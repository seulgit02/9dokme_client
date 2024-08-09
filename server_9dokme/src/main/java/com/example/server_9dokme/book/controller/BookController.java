package com.example.server_9dokme.book.controller;

import com.example.server_9dokme.book.dto.response.BookListDto;
import com.example.server_9dokme.book.dto.response.MyPageDto;
import com.example.server_9dokme.book.service.BookService;
import com.example.server_9dokme.member.dto.response.BookDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/mypage")
    public MyPageDto mypage(HttpSession session,
                            int id,
                            @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
                            @RequestParam(required = false, defaultValue = "lastViewedDate", value = "criteria") String criteria){
        return bookService.getMypageBookList(id, pageNo, criteria);
    }
}
