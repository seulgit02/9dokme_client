package com.example.server_9dokme.bookmark.exception;

import com.example.server_9dokme.bookmark.message.ErrorMessage;
import lombok.Getter;

@Getter
public class BookmarkException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public BookmarkException(ErrorMessage errorMessage) {
        super("[BookmarkException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
