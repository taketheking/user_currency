package com.sparta.currency_user.currency.repository;

import com.sparta.currency_user.currency.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    default Currency findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new RuntimeException("해당 유저를 찾을 수 없습니다."));
    }
}
