package com.sparta.currency_user.global.error;

import lombok.Getter;

@Getter
public class NotFoundByIdException extends BusinessException {

    public NotFoundByIdException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
