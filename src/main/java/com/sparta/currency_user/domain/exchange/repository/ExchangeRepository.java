package com.sparta.currency_user.domain.exchange.repository;

import com.sparta.currency_user.domain.exchange.dto.GroupByExchangeRequestDto;
import com.sparta.currency_user.domain.exchange.entity.Exchange;
import com.sparta.currency_user.domain.exchange.enums.ExchangeStatus;
import com.sparta.currency_user.global.error.ExceptionType;
import com.sparta.currency_user.global.error.NotFoundByIdException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    List<Exchange> findAllByUserId(Long user_id);

    default Exchange findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new NotFoundByIdException(ExceptionType.EXCHANGE_REQUEST_NOT_FOUND));
    }


    @Query("select new com.sparta.currency_user.domain.exchange.dto.GroupByExchangeRequestDto(count(ex), sum(ex.amountInKrw)) " +
            "from Exchange ex " +
            "join ex.user u "+
            "WHERE ex.status = :status and u.id = :userId " +
            "group by u.id")
    GroupByExchangeRequestDto findAllGroupByUserId(@Param("userId") Long userId, @Param("status") ExchangeStatus status);
}
