package com.example.server_9dokme.bookmark.controller;

import com.example.server_9dokme.bookmark.service.BookmarkService;
import com.example.server_9dokme.bookmark.service.dto.request.BookMarkRequest;
import com.example.server_9dokme.bookmark.service.dto.request.BookUnMarkRequest;
import com.example.server_9dokme.bookmark.service.dto.response.BookmarkResponse;
import com.example.server_9dokme.common.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookmarkController {

    private final BookmarkService bookmarkService;

    // 게시글 북마크 추가
    @PostMapping("/bookmark")
    public SuccessResponse<BookmarkResponse> addBookmark(@RequestBody BookMarkRequest bookMarkRequest) {
        BookmarkResponse response = bookmarkService.mark(bookMarkRequest);
        return SuccessResponse.success("북마크 반영 완료", response);
    }

    // 게시글 북마크 취소
    @DeleteMapping("/bookmark")
    public SuccessResponse<BookmarkResponse> removeBookmark(@RequestBody BookUnMarkRequest bookUnMarkRequest) {
        BookmarkResponse response = bookmarkService.unmark(bookUnMarkRequest);
        return SuccessResponse.success("북마크 해제 완료", response);
    }
}
