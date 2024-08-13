package com.example.server_9dokme.bookmark.service.dto.response;

public record BookmarkResponse(Long bookmarkId, boolean isMarked) {

    public static BookmarkResponse of(Long bookId, boolean isMarked) {
        return new BookmarkResponse(bookId, isMarked);
    }
}