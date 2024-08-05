package com.example.demo2.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorCode {
    ALREADY_REGISTERED_USER(HttpStatus.CONFLICT, "0001", "Already registered user");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public ApplicationException build() {
        return new ApplicationException(httpStatus, code, message);
    }

    public ApplicationException build(Object ...args) {
        return new ApplicationException(httpStatus, code, message.formatted(args));
    }
}
