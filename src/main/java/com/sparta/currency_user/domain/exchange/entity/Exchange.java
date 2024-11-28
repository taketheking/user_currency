package com.sparta.currency_user.domain.exchange.entity;

import com.sparta.currency_user.domain.base.entity.BaseEntity;
import com.sparta.currency_user.domain.currency.entity.Currency;
import com.sparta.currency_user.domain.exchange.enums.ExchangeStatus;
import com.sparta.currency_user.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "exchange")
public class Exchange extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // 환전 요청 고유 식별자


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;      // 고객 고유 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_currency_id")
    private Currency currency;      // 환전 대상 통화 식별자

    @Column(name = "amount_in_krw")
    private BigDecimal amountInKrw;       // 환전 전 금액

    @Column(name = "amount_after_exchange")
    private BigDecimal amountAfterExchange;       // 환전 후 금액

    @Enumerated(EnumType.STRING)
    private ExchangeStatus status;      // 상태


    public Exchange(User user, Currency currency, BigDecimal amount, BigDecimal amountAfterExchange, ExchangeStatus exchangeStatus) {
        this.user = user;
        this.currency = currency;
        this.amountInKrw = amount;
        this.amountAfterExchange = amountAfterExchange;
        this.status = exchangeStatus;

    }

    public Exchange() {

    }

    public void changeStatus(ExchangeStatus newStatus) {
        this.status = newStatus;
    }
}
