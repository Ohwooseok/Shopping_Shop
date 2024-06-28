package com.likelion.lionShop.Order.Service;

import com.likelion.lionShop.Item.Entity.Item;
import com.likelion.lionShop.Item.Repository.ItemRepository;
import com.likelion.lionShop.Order.Dto.request.CreateOrderRequestDto;
import com.likelion.lionShop.Order.Dto.request.UpdateOrderRequestDto;
import com.likelion.lionShop.Order.Dto.response.OrderResponseDto;
import com.likelion.lionShop.Order.Entity.Order;
import com.likelion.lionShop.Order.Repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public List<OrderResponseDto> createOrders(List<CreateOrderRequestDto> createOrderRequestDtoList) {
        return createOrderRequestDtoList.stream()
                .map(this::createOrder)
                .collect(Collectors.toList());
    }
    private OrderResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto){
        Order order = createOrderRequestDto.toEntity();

        // 주문한 상품의 Quantity만큼 해당 상품의 Stock 감소
        Optional<Item> item = itemRepository.findById(createOrderRequestDto.getItemId());
        item.get().removeStock(createOrderRequestDto.getQuantity());

        orderRepository.save(order);
        return OrderResponseDto.from(order);
    }

    public OrderResponseDto getOrder(Long orderId) {

        //DB에서 가져오는 로직이 들어갈 부분 (다음 주차)
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new IllegalArgumentException("상품이 존재하지 않습니다."));

        return OrderResponseDto.from(order);
    }

    @Transactional
    public void deleteOrder(Long orderId, CreateOrderRequestDto createOrderRequestDto) {
        // 취소한 Quantity 만큼 해당 Item의 Stock을 증가
        Optional<Item> item = itemRepository.findById(createOrderRequestDto.getItemId());
        item.get().plusStock(createOrderRequestDto.getQuantity());
        //DB에서 삭제하는 로직이 들어갈 부분 (다음 주차)
        orderRepository.deleteById(orderId);
        log.info("[Order Service] 주문 삭제하기 ID ---> {}", orderId);

    }

    @Transactional
    public OrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto) {
        //DB에서 가져오는 로직이 들어갈 부분
        Order order = orderRepository.findById(updateOrderRequestDto.getId()).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        order.update(updateOrderRequestDto);

        //DB에 저장 로직이 들어갈 부분
        orderRepository.save(order);
        log.info("[Order Service] 주문 수정하기 ID ---> {}", updateOrderRequestDto.getId());
        log.info("[Order Service] 주문 수정하기 이름 ---> {}",updateOrderRequestDto.getName());
        log.info("[Order Service] 주문 수정하기 수량 ---> {}", updateOrderRequestDto.getQuantity());
        log.info("[Order Service] 주문 수정하기 가격 ---> {}", updateOrderRequestDto.getPrice());

        return OrderResponseDto.from(order);
    }
}
