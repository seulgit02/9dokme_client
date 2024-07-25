package com.example.server_9dokme.book.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    INVALID_BOOK_ID(NOT_FOUND, "유효하지 않은 책 id 입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}