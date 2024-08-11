package com.example.server_9dokme.bookmark.service.dto.response;

public record BookmarkResponse(Long bookmarkId) {
    public static BookmarkResponse of(Long bookmarkId) {
        return new BookmarkResponse(bookmarkId);
    }
}