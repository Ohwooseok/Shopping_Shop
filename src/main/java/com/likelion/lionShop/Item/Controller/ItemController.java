package com.likelion.lionShop.Item.Controller;
import com.likelion.lionShop.Item.Dto.request.CreateItemRequestDto;
import com.likelion.lionShop.Item.Dto.request.UpdateItemRequestDto;
import com.likelion.lionShop.Item.Dto.response.ItemResponseDto;
import com.likelion.lionShop.Item.Service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("") // uri가 http://~/user로 시작하는 요청을 받습니다.
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/admin")
    public ResponseEntity<?> createItem(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CreateItemRequestDto createItemRequestDto) {

        ItemResponseDto itemResponseDto = itemService.createItem(createItemRequestDto, userDetails);
        return new ResponseEntity<>(itemResponseDto, HttpStatus.CREATED);
    }


    @GetMapping("/item/{itemId}")
    public ResponseEntity<?> getItem(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long itemId) {
        ItemResponseDto itemResponseDto = itemService.getItem(itemId);
        return ResponseEntity.ok(itemResponseDto);

    }
    @PutMapping("/item/{itemId}")
    public ResponseEntity<?> updateItem(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateItemRequestDto updateItemRequestDto){
        ItemResponseDto itemResponseDto = itemService.updateItem(updateItemRequestDto, userDetails);
        return ResponseEntity.ok(itemResponseDto);
    }


    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<Void> deleteItem(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long itemId) {
        itemService.deleteItem(itemId, userDetails);
        return ResponseEntity.noContent().build();
    }



}
