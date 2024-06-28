package com.likelion.lionShop.Item.Entity;

import com.likelion.lionShop.Item.Dto.request.UpdateItemRequestDto;
import com.likelion.lionShop.Order.Dto.request.UpdateOrderRequestDto;
import com.likelion.lionShop.Order.Entity.Order;
import com.likelion.lionShop.User.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품 고유 번호

    private String name;

    private int price;

    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;


    public void update(UpdateItemRequestDto updateItemRequestDto) {
        name = updateItemRequestDto.getName();
        price = updateItemRequestDto.getPrice();
        stock = updateItemRequestDto.getStock();
    }

    //주문한 수량만큼 Stock을 감소
    public void removeStock(int stockNumber) {
        int restStock = this.stock - stockNumber;
        if(restStock < 0) {
            throw new RuntimeException("상품의 재고가 부족합니다." +
                    "(현재 재고 수량: " + this.stock + ")");
        }
        this.stock = restStock;
    }

    public void plusStock(int stockNumber) {
        this.stock += stockNumber;
    }


}
