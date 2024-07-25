package com.example.server_9dokme.book.exception;

import com.example.server_9dokme.book.message.ErrorMessage;
import lombok.Getter;

@Getter
public class BookException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public BookException(ErrorMessage errorMessage) {
        super("[MemberException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
