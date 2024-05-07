package com.likelion.lionShop.Dto.request;

import com.likelion.lionShop.entity.Order;
import com.likelion.lionShop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.
@Getter
public class CreateOrderRequestDto {

    //상품 이름
    public String name;

    //수량
    public int quantity;

    //가격
    public int price;

    public User user;
    public Order toEntity(){
        return Order.builder()
                .name(name)
                .quantity(quantity)
                .price(price)
                .user(user)
                .build();
    }

}