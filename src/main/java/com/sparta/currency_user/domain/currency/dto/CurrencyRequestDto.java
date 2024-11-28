package com.sparta.currency_user.domain.currency.dto;

import com.sparta.currency_user.domain.currency.entity.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotBlank
    private String currencyName;

    @NotNull
    private BigDecimal exchangeRate;

    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
