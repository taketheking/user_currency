package com.sparta.currency_user.domain.exchange.repository;

import com.sparta.currency_user.domain.exchange.entity.Exchange;
import com.sparta.currency_user.global.error.ExceptionType;
import com.sparta.currency_user.global.error.NotFoundByIdException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    List<Exchange> findAllByUserId(Long user_id);

    default Exchange findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new NotFoundByIdException(ExceptionType.EXCHANGE_REQUEST_NOT_FOUND));
    }
}
