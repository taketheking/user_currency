package com.sparta.currency_user.domain.currency.repository;

import com.sparta.currency_user.domain.currency.entity.Currency;
import com.sparta.currency_user.global.error.ExceptionType;
import com.sparta.currency_user.global.error.NotFoundByIdException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    default Currency findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new NotFoundByIdException(ExceptionType.CURRENCY_NOT_FOUND));
    }
}
