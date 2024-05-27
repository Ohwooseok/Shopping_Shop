package com.likelion.lionShop.Item.Dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.
@Getter
public class UpdateItemRequestDto {

    public Long id;

    public int price;

    public int stock;

    public String name;
}
