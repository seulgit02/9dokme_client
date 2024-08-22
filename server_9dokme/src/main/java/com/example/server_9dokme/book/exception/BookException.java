package com.example.server_9dokme.book.exception;

import com.example.server_9dokme.book.message.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public BookException(ErrorMessage errorMessage) {
        super("[BookException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
    public HttpStatus getHttpStatus() {
        return this.errorMessage.getHttpStatus();
    }
}
