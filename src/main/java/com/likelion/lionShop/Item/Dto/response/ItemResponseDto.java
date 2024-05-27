package com.likelion.lionShop.Item.Dto.response;
import com.likelion.lionShop.Item.Entity.Item;
import com.likelion.lionShop.Order.Dto.response.OrderResponseDto;
import com.likelion.lionShop.Order.Entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ItemResponseDto {
    public Long id;
    public String name;
    public int price;
    public int stock;

    public static ItemResponseDto from(Item item){
        return ItemResponseDto.builder()
                .id(item.getId())
                .name(item.getName())
                .stock(item.getStock())
                .price(item.getPrice())
                .build();
    }
}
