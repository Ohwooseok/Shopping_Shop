package com.likelion.lionShop.Order.Entity;

import com.likelion.lionShop.Item.Entity.Item;
import com.likelion.lionShop.Order.Dto.request.UpdateOrderRequestDto;
import com.likelion.lionShop.User.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int quantity;

    @Column
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items;

    public void update(UpdateOrderRequestDto updateOrderRequestDto) {
        name = updateOrderRequestDto.getName();
        quantity = updateOrderRequestDto.getQuantity();
        price = updateOrderRequestDto.getPrice();
    }
}
