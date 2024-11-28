package com.sparta.currency_user.domain.user.repository;

import com.sparta.currency_user.domain.user.entity.User;
import com.sparta.currency_user.global.error.ExceptionType;
import com.sparta.currency_user.global.error.NotFoundByIdException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new NotFoundByIdException(ExceptionType.USER_NOT_FOUND));
    }
}
