package com.example.server_9dokme.inquiring.exception;

import com.example.server_9dokme.inquiring.message.ErrorMessage;
import lombok.Getter;

@Getter
public class InquireException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public InquireException(ErrorMessage errorMessage) {
        super("[InquiringException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}