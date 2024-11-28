package com.sparta.currency_user.exchange.controller;

import com.sparta.currency_user.exchange.dto.ExchangeRequestDto;
import com.sparta.currency_user.exchange.dto.ExchangeResponseDto;
import com.sparta.currency_user.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    /**
     *  환전 요청 수행 API
     *
     * @param exchangeRequestDto  환전 요청 정보 : 1. 유저 Id, 2. 환전 통화, 3. 환전 금액(원화)
     * @return ResponseEntity<ExchangeResponseDto>  환전 요청 수행 결과 응답
     *
     */
    @PostMapping
    public ResponseEntity<ExchangeResponseDto> exchangeRequest(@RequestBody @Validated ExchangeRequestDto exchangeRequestDto) {
        return new ResponseEntity<>(exchangeService.exchange(exchangeRequestDto), HttpStatus.CREATED);
    }

    /**
     *  특정 고객이 요구한 환전 요청 리스트 조회 API
     *
     * @param userId  특정 고객 id
     * @return ResponseEntity<ExchangeResponseDto>  특정 고객이 요구한 환전 요청 조회 결과 응답
     *
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<ExchangeResponseDto>> findExchangeRequestsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(exchangeService.findAllByUserId(userId), HttpStatus.OK);
    }

    /**
     *  특정 환전 요청 상태를 취소로 변경 API
     *
     * @param id  환전 요청 id
     * @return ResponseEntity<ExchangeResponseDto>  취소된 요청 정보 전달
     *
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> cancelExchangeRequest(@PathVariable Long id) {
        return new ResponseEntity<>(exchangeService.ChangeRequestStatus2Cancel(id), HttpStatus.OK);
    }

}
