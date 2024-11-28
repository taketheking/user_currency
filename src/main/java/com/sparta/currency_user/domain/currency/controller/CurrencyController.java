package com.sparta.currency_user.domain.currency.controller;

import com.sparta.currency_user.domain.currency.dto.CurrencyRequestDto;
import com.sparta.currency_user.domain.currency.dto.CurrencyResponseDto;
import com.sparta.currency_user.domain.currency.service.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     *  통화 정보 등록 API
     *
     * @param currencyRequestDto    통화 정보를 입력한 요청 Dto
     * @return ResponseEntity<CurrencyResponseDto>  통화 정보 및 http 상태 전달
     *
     */
    @PostMapping
    public ResponseEntity<CurrencyResponseDto> createCurrency(@RequestBody @Valid CurrencyRequestDto currencyRequestDto) {
        return ResponseEntity.ok().body(currencyService.save(currencyRequestDto));
    }

    /**
     *  통화 정보 리스트 조회 API
     *
     * @return ResponseEntity<List<CurrencyResponseDto>>  통화 정보 리스트 및 http 상태 전달
     *
     */
    @GetMapping
    public ResponseEntity<List<CurrencyResponseDto>> findCurrencies() {
        return ResponseEntity.ok().body(currencyService.findAll());
    }

    /**
     *  특정 통화 정보 조회 API
     *
     * @param id    특정 통화의 id
     * @return ResponseEntity<CurrencyResponseDto>  통화 정보 및 http 상태 전달
     *
     */
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> findCurrency(@PathVariable Long id) {
        return ResponseEntity.ok().body(currencyService.findById(id));
    }
}
