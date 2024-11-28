package com.sparta.currency_user.global.error;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {
    private final ExceptionType exceptionType;

    public BusinessException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
}
