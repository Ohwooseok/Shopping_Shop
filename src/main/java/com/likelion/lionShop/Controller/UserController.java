package com.likelion.lionShop.Controller;

import com.likelion.lionShop.Dto.CreateUserRequestDto;
import com.likelion.lionShop.Dto.UpdateUserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j //로그 출력을 도와주는 어노테이션
@RestController
@RequestMapping("/user") // uri가 http://~/user로 시작하는 요청을 받습니다.
public class UserController {

    // 1. 사용자를 생성하는 컨트롤러를 만듭니다.
    // 이때 log.info를 이용하여 사용자의 이름, 주소, ID, PW를 출력해줍니다. return 값은 "사용자 생성"입니다.
    @PostMapping("")
    public String createUser(@RequestBody CreateUserRequestDto createUserRequestDto){
        log.info(createUserRequestDto.getName());
        log.info(createUserRequestDto.getAddress());
        log.info(createUserRequestDto.getId());
        log.info(createUserRequestDto.getPassword());
        return "사용자 생성";
    }
    // 2. 사용자를 조회하는 컨트롤러를 만듭니다.
    // 이때 log.info를 이용하여 사용자의 ID를 출력해줍니다. return 값은 "사용자 조회"입니다.
    @GetMapping("{userId}")
    public String getUser(@PathVariable Long userId){
        log.info(String.valueOf(userId));
        return "사용자 조회";
    }
    // 3. 사용자를 수정하는 컨트롤러를 만듭니다.
    // 이때 log.info를 이용를여 사용자의 이름, 주소를 출력해줍니다. return 값은 "사용자 수정"입니다.
    @PutMapping("")
    public String updateUser(@RequestBody UpdateUserRequestDto updateUserRequestDto, @PathVariable long userId){
        log.info(updateUserRequestDto.getName());
        log.info(updateUserRequestDto.getAddress());
        return "사용자 수정";
    }
    // 4. 사용자를 삭제하는 컨트롤러를 만듭니다.
    // 이때 log.info 이용하여 사용자의 ID를 출력해줍tm니다. return 값은 "사용자 삭제"입니다.
    @DeleteMapping("/{userId}")
    public String deleteUser(@RequestParam("id") Long userId){
        log.info(String.valueOf(userId));
        return "사용자 삭제";
    }
}