package com.sparta.currency_user.exchange.dto;

import com.sparta.currency_user.exchange.entity.Exchange;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ExchangeResponseDto {
    private final Long id;

    private final String currencyName;
    private final BigDecimal amountAfterExchange;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public ExchangeResponseDto(Long id, String currencyName, BigDecimal amountAfterExchange, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.currencyName = currencyName;
        this.amountAfterExchange = amountAfterExchange;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public static ExchangeResponseDto toDto(Exchange exchange) {
        return new ExchangeResponseDto(
                exchange.getId(),
                exchange.getCurrency().getCurrencyName(),
                exchange.getAmountAfterExchange(),
                exchange.getCreatedAt(),
                exchange.getModifiedAt()
        );

    }
}