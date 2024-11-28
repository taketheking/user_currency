package com.sparta.currency_user.domain.currency.enums;

import lombok.Getter;

@Getter
public enum CurrencyDigit {
    USD(1), JPY(100), EUR(1), CNY(1), GBP(1),
    CAD(1), VND(100);

    private final int CurrencyDigit;

    CurrencyDigit(int currencyDigit) {
        CurrencyDigit = currencyDigit;
    }
}

