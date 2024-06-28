package com.likelion.lionShop.User.Entity;

import com.likelion.lionShop.User.Dto.request.UpdateUserRequestDto;
import com.likelion.lionShop.Order.Entity.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "is_staff", nullable = false)
    @ColumnDefault("false")
    private boolean isStaff;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public void update(UpdateUserRequestDto userRequestDto) {
        name = userRequestDto.getEmail();
        address = userRequestDto.getAddress();
    }
}
