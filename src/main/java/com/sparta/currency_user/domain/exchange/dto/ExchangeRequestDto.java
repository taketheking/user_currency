package com.sparta.currency_user.domain.exchange.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    @NotNull
    private final Long userId;

    @NotNull
    private final Long currencyId;

    private final BigDecimal amount;

    public ExchangeRequestDto(Long userId, Long currencyId, BigDecimal amount) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amount = amount;
    }
}
