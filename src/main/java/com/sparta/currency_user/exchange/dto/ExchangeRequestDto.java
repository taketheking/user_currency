package com.sparta.currency_user.exchange.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {
    private final Long userId;
    private final Long currencyId;
    private final BigDecimal amount;

    public ExchangeRequestDto(Long userId, Long currencyId, BigDecimal amount) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amount = amount;
    }
}
