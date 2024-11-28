package com.sparta.currency_user.user.entity;

import com.sparta.currency_user.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {}
}