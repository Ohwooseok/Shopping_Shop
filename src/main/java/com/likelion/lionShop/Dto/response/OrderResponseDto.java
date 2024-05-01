package com.likelion.lionShop.Dto.response;

import com.likelion.lionShop.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OrderResponseDto {
    public Long id;
    public String name;
    public int quantity;
    public int price;

    public static OrderResponseDto from(Order order){
        return OrderResponseDto.builder()
                .id(order.getId())
                .name(order.getName())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .build();
    }


}
