package com.sparta.currency_user.domain.exchange.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class GroupByExchangeRequestDto {

    private final Long count;

    private final BigDecimal totalAmountInKrw;

    public GroupByExchangeRequestDto(Long count, BigDecimal totalAmountInKrw) {
        this.count = count;
        this.totalAmountInKrw = totalAmountInKrw;
    }

}
