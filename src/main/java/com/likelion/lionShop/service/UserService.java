package com.likelion.lionShop.service;

import com.likelion.lionShop.Dto.request.CreateUserRequestDto;
import com.likelion.lionShop.Dto.request.UpdateUserRequestDto;
import com.likelion.lionShop.Dto.response.UserResponseDto;
import com.likelion.lionShop.entity.User;
import com.likelion.lionShop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto){
        User user = createUserRequestDto.toEntity();
        userRepository.save(user);
        return UserResponseDto.from(user);
    }

    public UserResponseDto getUser(Long userId){
        log.info("[User Service] 유저 가져오기 ID ---> {}", userId);

        //DB에서 가져오는 로직이 들어갈 부분 (다음 주차)
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return UserResponseDto.from(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        log.info("[User Service] 유저 삭제하기 ID ---> {}", userId);

        //DB에서 삭제하는 로직이 들어갈 부분 (다음 주차)
        userRepository.deleteById(userId);
    }

    @Transactional
    public UserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto) {
        log.info("[User Service] 주문 수정하기 ID ---> {}", updateUserRequestDto.getId());
        //DB에서 가져오는 로직이 들어갈 부분 (다음 주차)
        User user = userRepository.findById(updateUserRequestDto.getId()).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
        //DB에서 수정하는 로직이 들어갈 부분
        user.update(updateUserRequestDto);

        //DB에 저장 로직이 들어갈 부분 (다음 주차)
        userRepository.save(user);

        return UserResponseDto.from(user);
    }
}
