package com.sparta.currency_user.exchange.repository;

import com.sparta.currency_user.exchange.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    List<Exchange> findAllByUserId(Long user_id);

    default Exchange findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new RuntimeException("해당 환전 요청을 찾을 수 없습니다."));
    }
}
