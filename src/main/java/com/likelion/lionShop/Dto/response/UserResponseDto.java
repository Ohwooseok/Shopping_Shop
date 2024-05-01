package com.likelion.lionShop.Dto.response;

import com.likelion.lionShop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserResponseDto {
    public Long id;
    public String name;
    public String address;

    public static UserResponseDto from(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .build();
    }

}
