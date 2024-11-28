package com.sparta.currency_user.domain.exchange.service;

import com.sparta.currency_user.domain.currency.entity.Currency;
import com.sparta.currency_user.domain.currency.enums.CurrencyDigit;
import com.sparta.currency_user.domain.currency.repository.CurrencyRepository;
import com.sparta.currency_user.domain.exchange.dto.ExchangeRequestDto;
import com.sparta.currency_user.domain.exchange.dto.ExchangeResponseDto;
import com.sparta.currency_user.domain.exchange.dto.GroupByExchangeRequestDto;
import com.sparta.currency_user.domain.exchange.entity.Exchange;
import com.sparta.currency_user.domain.exchange.enums.ExchangeStatus;
import com.sparta.currency_user.domain.exchange.repository.ExchangeRepository;
import com.sparta.currency_user.domain.user.entity.User;
import com.sparta.currency_user.domain.user.repository.UserRepository;
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

        // 통화별 자리수 가져오기
        int digit = CurrencyDigit.valueOf(currency.getCurrencyName()).getCurrencyDigit();

        BigDecimal amountAfterExchange = exchangeRequestDto.
                getAmount().
                divide(currency.getExchangeRate(), 2, RoundingMode.HALF_UP);

        Exchange exchange = new Exchange(user,
                currency,
                exchangeRequestDto.getAmount(),
                amountAfterExchange.multiply(BigDecimal.valueOf(digit)),
                ExchangeStatus.NORMAL
        );

        // 영속성 컨텍스트가 알아서 저장함. but 명시
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
     * lv.5 특정 고객의 환전 요청을 그룹화하여 조회
     *
     * 상태 확인도 추가 - 취소된 요청은 제외
     */
    public GroupByExchangeRequestDto findAllGroupByUserId(Long userId) {
        return exchangeRepository.findAllGroupByUserId(userId, ExchangeStatus.NORMAL);
    }

    /*
     * 특정 환전 요청 상태를 취소로 변경
     */
    public ExchangeResponseDto ChangeRequestStatus2Cancel(Long id) {
        Exchange exchange = exchangeRepository.findByIdOrElseThrow(id);

        exchange.changeStatus(ExchangeStatus.CANCELLED);

        // 영속성 컨텍스트가 알아서 저장함. but 명시
        exchangeRepository.save(exchange);

        return ExchangeResponseDto.toDto(exchange);
    }
}
