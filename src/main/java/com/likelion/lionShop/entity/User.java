package com.likelion.lionShop.entity;

import com.likelion.lionShop.Dto.request.UpdateUserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String email;

    @Column
    private String password;

    @Column
    private String roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public void update(UpdateUserRequestDto userRequestDto) {
        name = userRequestDto.getEmail();
        address = userRequestDto.getAddress();
    }
}
