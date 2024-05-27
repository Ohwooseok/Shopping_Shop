package com.likelion.lionShop.Item.Service;
import com.likelion.lionShop.Item.Dto.request.CreateItemRequestDto;
import com.likelion.lionShop.Item.Dto.request.UpdateItemRequestDto;
import com.likelion.lionShop.Item.Dto.response.ItemResponseDto;
import com.likelion.lionShop.Item.Entity.Item;
import com.likelion.lionShop.Item.Repository.ItemRepository;
import com.likelion.lionShop.User.Entity.User;
import com.likelion.lionShop.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Transactional
    public ItemResponseDto createItem(CreateItemRequestDto createItemRequestDto, UserDetails userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        if (!user.isStaff()){
            throw new IllegalArgumentException("올바르지 않은 권한입니다.");
        }
        Item item = createItemRequestDto.toEntity();
        itemRepository.save(item);
        return ItemResponseDto.from(item);
    }

    public ItemResponseDto getItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        return ItemResponseDto.from(item);
    }


    @Transactional
    public void deleteItem(Long itemId, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        if (!user.isStaff()){
            throw new IllegalArgumentException("올바르지 않은 권한입니다.");
        }
        itemRepository.deleteById(itemId);

        log.info("[Item Service] 상품 삭제하기 ID ---> {}", itemId);

    }

    @Transactional
    public ItemResponseDto updateItem(UpdateItemRequestDto updateItemRequestDto, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        if (!user.isStaff()){
            throw new IllegalArgumentException("올바르지 않은 권한입니다.");
        }

        Item item = itemRepository.findById(updateItemRequestDto.getId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        item.update(updateItemRequestDto);

        itemRepository.save(item);

        log.info("[Item Service] 상품 수정하기 ID ---> {}", updateItemRequestDto.getId());
        log.info("[Item Service] 상품 수정하기 이름 ---> {}",updateItemRequestDto.getName());
        log.info("[Item Service] 상품 수정하기 수량 ---> {}", updateItemRequestDto.getStock());
        log.info("[Item Service] 상품 수정하기 가격 ---> {}", updateItemRequestDto.getPrice());

        return ItemResponseDto.from(item);
    }



}
