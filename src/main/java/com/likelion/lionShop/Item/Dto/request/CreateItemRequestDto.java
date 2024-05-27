package com.likelion.lionShop.Item.Dto.request;
import com.likelion.lionShop.Item.Entity.Item;
import com.likelion.lionShop.Order.Entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.
@Getter
public class CreateItemRequestDto {
    public String name;
    public int price;
    public int stock;
    public Order order;

    public Item toEntity() {
        return Item.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .order(order)
                .build();
    }

}
