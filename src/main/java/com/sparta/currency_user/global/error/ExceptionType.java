package com.sparta.currency_user.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {

    DELETED_USER(HttpStatus.BAD_REQUEST, "이미 삭제된 유저입니다."),
    USER_NOT_MATCH(HttpStatus.BAD_REQUEST, "잘못된 유저의 정보에 접근하고 있습니다."),
    BAD_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호는 영문자, 숫자, 특수문자를 포함하며 8자 이상이어야 합니다."),
    NOT_LOGIN(HttpStatus.UNAUTHORIZED, "로그인을 해주세요."),
    ALREADY_LOGIN(HttpStatus.UNAUTHORIZED, "이미 로그인한 사용자입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,  "해당 유저의 정보를 찾을 수 없습니다."),
    EXCHANGE_REQUEST_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 환전 요청을 찾을 수 없습니다."),
    CURRENCY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 통화의 정보를 찾을 수 없습니다."),
    FAIL_FILE_UPLOADED(HttpStatus.INTERNAL_SERVER_ERROR,  "해당 파일 업로드에 실패하였습니다. 잘못된 형식의 확장자입니다."),
    FAIL_FILE_DOWNLOADED(HttpStatus.INTERNAL_SERVER_ERROR,  "해당 파일 다운로드에 실패하였습니다.");

    private final HttpStatus status;
    private final String message;

    ExceptionType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
