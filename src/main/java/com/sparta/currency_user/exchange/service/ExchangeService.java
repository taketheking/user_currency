package com.sparta.currency_user.exchange.service;

import com.sparta.currency_user.currency.entity.Currency;
import com.sparta.currency_user.currency.repository.CurrencyRepository;
import com.sparta.currency_user.exchange.dto.ExchangeRequestDto;
import com.sparta.currency_user.exchange.dto.ExchangeResponseDto;
import com.sparta.currency_user.exchange.entity.Exchange;
import com.sparta.currency_user.exchange.enums.ExchangeStatus;
import com.sparta.currency_user.exchange.repository.ExchangeRepository;
import com.sparta.currency_user.user.entity.User;
import com.sparta.currency_user.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ExchangeService {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final ExchangeRepository exchangeRepository;


    @Autowired
    public ExchangeService(UserRepository userRepository, CurrencyRepository currencyRepository, ExchangeRepository exchangeRepository) {
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.exchangeRepository = exchangeRepository;
    }


    /*
     * 환전 요청 수행
     */
    public ExchangeResponseDto exchange(ExchangeRequestDto exchangeRequestDto) {
        User user = userRepository.findByIdOrElseThrow(exchangeRequestDto.getUserId());
        Currency currency = currencyRepository.findByIdOrElseThrow(exchangeRequestDto.getCurrencyId());

        BigDecimal amountAfterExchange = exchangeRequestDto.
                getAmount().
                divide(currency.getExchangeRate(), 2, RoundingMode.HALF_UP);

        Exchange exchange = new Exchange(user,
                currency,
                exchangeRequestDto.getAmount(),
                amountAfterExchange,
                ExchangeStatus.NORMAL
        );

        exchangeRepository.save(exchange);

        return ExchangeResponseDto.toDto(exchange);
    }

    /*
     * 특정 고객의 환전 요청 리스트 조회
     */
    public List<ExchangeResponseDto> findAllByUserId(Long userId) {
        return exchangeRepository.findAllByUserId(userId).stream().map(ExchangeResponseDto::toDto).toList();
    }

    /*
     * 특정 환전 요청 상태를 취소로 변경
     */
    public ExchangeResponseDto ChangeRequestStatus2Cancel(Long id) {
        Exchange exchange = exchangeRepository.findByIdOrElseThrow(id);

        exchange.changeStatus(ExchangeStatus.CANCELLED);

        // 영속성 컨텍스트가 알아서 저장함.
        // exchangeRepository.save(exchange);

        return ExchangeResponseDto.toDto(exchange);
    }
}
