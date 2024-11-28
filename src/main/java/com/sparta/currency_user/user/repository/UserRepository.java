package com.sparta.currency_user.user.repository;

import com.sparta.currency_user.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new RuntimeException("해당 유저를 찾을 수 없습니다."));
    }
}
