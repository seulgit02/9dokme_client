package com.example.server_9dokme.member.exception;

import com.example.server_9dokme.member.message.ErrorMessage;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public MemberException(ErrorMessage errorMessage) {
        super("[MemberException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
