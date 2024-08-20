package com.example.server_9dokme.common.handler;
import com.example.server_9dokme.book.exception.BookException;
import com.example.server_9dokme.common.dto.BaseResponse;
import com.example.server_9dokme.common.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BookException.class)
    public ResponseEntity<ErrorResponse> bookException(BookException exception) {
        log.error(exception.getMessage());
        return ResponseEntity
                .status(exception.getErrorMessage().getHttpStatus())
                .body(ErrorResponse.of(exception.getMessage(), exception.getErrorMessage().getHttpStatus()));
    }
}