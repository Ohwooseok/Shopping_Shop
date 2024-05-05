package com.likelion.lionShop.Controller;

import com.likelion.lionShop.Dto.request.CreateUserRequestDto;
import com.likelion.lionShop.Dto.request.UpdateUserRequestDto;
import com.likelion.lionShop.Dto.response.UserResponseDto;
import com.likelion.lionShop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j //로그 출력을 도와주는 어노테이션
@RestController
@RequiredArgsConstructor
@RequestMapping("/user") // uri가 http://~/user로 시작하는 요청을 받습니다.
public class UserController {

    private final UserService userService;
    // 1. 사용자를 생성하는 컨트롤러를 만듭니다.
    // 이때 log.info를 이용하여 사용자의 이름, 주소, ID, PW를 출력해줍니다. return 값은 "사용자 생성"입니다.
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto createUserRequestDto){
        UserResponseDto userResponseDto = userService.createUser(createUserRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
    // 2. 사용자를 조회하는 컨트롤러를 만듭니다.
    // 이때 log.info를 이용하여 사용자의 ID를 출력해줍니다. return 값은 "사용자 조회"입니다.
    @GetMapping("{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }
    // 3. 사용자를 수정하는 컨트롤러를 만듭니다.
    // 이때 log.info를 이용를여 사용자의 이름, 주소를 출력해줍니다. return 값은 "사용자 수정"입니다.
    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequestDto updateUserRequestDto){
        return ResponseEntity.ok(userService.updateUser(updateUserRequestDto));
    }
    // 4. 사용자를 삭제하는 컨트롤러를 만듭니다.
    // 이때 log.info 이용하여 사용자의 ID를 출력해줍tm니다. return 값은 "사용자 삭제"입니다.
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@RequestParam("id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}