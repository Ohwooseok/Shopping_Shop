package com.likelion.lionShop.Item.Repository;

import com.likelion.lionShop.Item.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
