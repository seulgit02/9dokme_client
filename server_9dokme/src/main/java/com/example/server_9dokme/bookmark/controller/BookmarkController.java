package com.example.server_9dokme.bookmark.controller;

import com.example.server_9dokme.bookmark.service.BookmarkService;
import com.example.server_9dokme.bookmark.service.dto.request.BookMarkRequest;
import com.example.server_9dokme.bookmark.service.dto.request.BookUnMarkRequest;
import com.example.server_9dokme.bookmark.service.dto.response.BookmarkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmark")
@RequiredArgsConstructor
@Slf4j
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    // 게시글 북마크 추가
    @PostMapping
    public BookmarkResponse addBookmark(@RequestParam Long BookId, Long memberId) {
        return bookmarkService.mark(BookId, memberId);
    }

    // 게시글 북마크 취소
    @DeleteMapping
    public BookmarkResponse removeBookmark(@RequestBody BookUnMarkRequest bookUnMarkRequest) {
        return bookmarkService.unmark(bookUnMarkRequest);
    }
}
