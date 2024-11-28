package com.sparta.currency_user.domain.user.controller;

import com.sparta.currency_user.domain.user.dto.UserRequestDto;
import com.sparta.currency_user.domain.user.dto.UserResponseDto;
import com.sparta.currency_user.domain.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *  회원 가입(사용자 생성) API
     *
     * @param userRequestDto    사용자 정보를 입력한 요청 Dto
     * @return ResponseEntity<UserResponseDto>  사용자 정보 및 http 상태 전달
     *
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(userService.save(userRequestDto));
    }

    /**
     *  사용자 전체 조회 API
     *
     * @return ResponseEntity<List<UserResponseDto>>  사용자들 정보 및 http 상태 전달
     *
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    /**
     *  사용자 단건 조회 API
     *
     * @param id    사용자 id
     * @return ResponseEntity<UserResponseDto>  사용자 정보 및 http 상태 전달
     *
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    /**
     *  사용자 삭제 API
     *
     * @param id    사용자 id
     * @return ResponseEntity<String>  http 상태 및 응답 메세지 전달
     *
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("정상적으로 삭제되었습니다.");
    }
}
