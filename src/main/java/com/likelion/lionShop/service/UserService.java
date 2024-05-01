package com.likelion.lionShop.service;

import com.likelion.lionShop.Dto.request.CreateUserRequestDto;
import com.likelion.lionShop.Dto.request.UpdateUserRequestDto;
import com.likelion.lionShop.Dto.response.UserResponseDto;
import com.likelion.lionShop.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public void createUser(CreateUserRequestDto createUserRequestDto){
        log.info("[User Service] 유저 생성 이름 ---> {}", createUserRequestDto.getName());

        //DTO를 Entity로 변환
        User user = createUserRequestDto.toEntity();

        //DB에 저장 로직이 들어갈 부분 (다음 주차)
    }

    public UserResponseDto getUser(Long userId){
        log.info("[User Service] 유저 가져오기 ID ---> {}", userId);

        //DB에서 가져오는 로직이 들어갈 부분 (다음 주차)

        //(임시) User Entity
        User user = new User();

        return UserResponseDto.from(user);
    }

    public void deleteUser(Long userId) {
        log.info("[User Service] 유저 삭제하기 ID ---> {}", userId);

        //DB에서 삭제하는 로직이 들어갈 부분 (다음 주차)
    }

    public void updateUser(UpdateUserRequestDto updateUserRequestDto) {
        log.info("[User Service] 주문 수정하기 ID ---> {}", updateUserRequestDto.getId());
        //DB에서 가져오는 로직이 들어갈 부분 (다음 주차)
        //임시 Order Entity
        User user = new User();

        //DB에서 수정하는 로직이 들어갈 부분
        user.update(updateUserRequestDto);

        //DB에 저장 로직이 들어갈 부분 (다음 주차)
    }
}
