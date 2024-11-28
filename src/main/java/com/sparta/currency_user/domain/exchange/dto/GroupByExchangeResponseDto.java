package com.sparta.currency_user.domain.exchange.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class GroupByExchangeResponseDto {

    private final Long count;

    private final BigDecimal totalAmountInKrw;

    public GroupByExchangeResponseDto(Long count, BigDecimal totalAmountInKrw) {
        this.count = count;
        this.totalAmountInKrw = totalAmountInKrw;
    }

}
