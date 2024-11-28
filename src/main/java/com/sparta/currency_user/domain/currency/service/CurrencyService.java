package com.sparta.currency_user.domain.currency.service;

import com.sparta.currency_user.domain.currency.dto.CurrencyRequestDto;
import com.sparta.currency_user.domain.currency.dto.CurrencyResponseDto;
import com.sparta.currency_user.domain.currency.entity.Currency;
import com.sparta.currency_user.domain.currency.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Transactional
    public CurrencyResponseDto save(CurrencyRequestDto currencyRequestDto) {
        Currency savedCurrency = currencyRepository.save(currencyRequestDto.toEntity());
        return new CurrencyResponseDto(savedCurrency);
    }

    public CurrencyResponseDto findById(Long id) {
        return new CurrencyResponseDto(findCurrencyById(id));
    }

    public Currency findCurrencyById(Long id) {
        return currencyRepository.findByIdOrElseThrow(id);
    }

    public List<CurrencyResponseDto> findAll() {
        return currencyRepository.findAll().stream().map(CurrencyResponseDto::toDto).toList();
    }

    /*
    * 통화 테이블에 있는 통화들의 환율이 유효한지 확인을 위한 메서드
    * CurrencyService 이 빈으로 초기화되어 생성되고 의존성 주입 후에 호출됨
    * 순서 : CurrencyService 생성자 호출 -> 사용되는 곳에 의존성 주입 -> validateCurrency 실행
    * 초기화 작업 및 검사 작업에 사용
     */
    @PostConstruct
    public void validateCurrency() {
        List<Currency> allCurrencies = currencyRepository.findAll();

        allCurrencies.stream()
                .filter(currency -> currency.getExchangeRate().intValue() <= 0 || currency.getExchangeRate().intValue() >= 100000)
                .forEach(currency -> log.warn("{}은  유효하지 않은 환율을 가지고 있습니다.", currency.getCurrencyName()));
    }
}
