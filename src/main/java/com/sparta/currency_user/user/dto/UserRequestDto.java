package com.sparta.currency_user.user.dto;

import com.sparta.currency_user.user.entity.User;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String name;
    private String email;

    public User toEntity() {
        return new User(
                this.name,
                this.email
        );
    }
}
