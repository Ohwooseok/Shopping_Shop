package com.likelion.lionShop.entity;

import com.likelion.lionShop.Dto.request.UpdateUserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String loginId;

    @Column
    private String password;

    public void update(UpdateUserRequestDto userRequestDto) {
        name = userRequestDto.getName();
        address = userRequestDto.getAddress();
    }
}
