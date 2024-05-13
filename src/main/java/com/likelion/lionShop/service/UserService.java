package com.likelion.lionShop.service;

import com.likelion.lionShop.Dto.request.CreateUserRequestDto;
import com.likelion.lionShop.Dto.request.UpdateUserRequestDto;
import com.likelion.lionShop.Dto.response.UserResponseDto;
import com.likelion.lionShop.entity.User;
import com.likelion.lionShop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto){
        User user = createUserRequestDto.toEntity(passwordEncoder);
        userRepository.save(user);
        return UserResponseDto.from(user);
    }

    public UserResponseDto getUser(String email){
        //DB에서 가져오는 로직이 들어갈 부분 (다음 주차)
        User user = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return UserResponseDto.from(user);
    }

    @Transactional
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));

        userRepository.delete(user);

        log.info("[User Delete] 유저가 삭제되었습니다 ---> {}", user.getEmail());

    }

    @Transactional
    public UserResponseDto updateUser(String email, UpdateUserRequestDto updateUserRequestDto) {
        //DB에서 가져오는 로직이 들어갈 부분 (다음 주차)
        User user = userRepository.findByEmail(updateUserRequestDto.getEmail()).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
        //DB에서 수정하는 로직이 들어갈 부분
        user.update(updateUserRequestDto);

        //DB에 저장 로직이 들어갈 부분 (다음 주차)
        userRepository.save(user);

        log.info("[User Service] 유저 수정하기 ID ---> {}", updateUserRequestDto.getId());
        log.info("[User Service] 유저 수정하기 Email ---> {}", updateUserRequestDto.getEmail());
        log.info("[User Service] 유저 수정하기 Address ---> {}", updateUserRequestDto.getAddress());


        return UserResponseDto.from(user);
    }
}
