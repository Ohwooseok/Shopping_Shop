package com.likelion.lionShop.service;

import com.likelion.lionShop.Dto.request.CreateOrderRequestDto;
import com.likelion.lionShop.Dto.request.UpdateOrderRequestDto;
import com.likelion.lionShop.Dto.response.OrderResponseDto;
import com.likelion.lionShop.entity.Order;
import com.likelion.lionShop.repository.OrderRepository;
import com.likelion.lionShop.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public List<OrderResponseDto> createOrders(List<CreateOrderRequestDto> createOrderRequestDtoList) {
        return createOrderRequestDtoList.stream()
                .map(this::createOrder)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto){
        Order order = createOrderRequestDto.toEntity();
        orderRepository.save(order);
        return OrderResponseDto.from(order);
    }

    public OrderResponseDto getOrder(Long orderId) {

        //DB에서 가져오는 로직이 들어갈 부분 (다음 주차)
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new IllegalArgumentException("상품이 존재하지 않습니다."));

        return OrderResponseDto.from(order);
    }

    @Transactional
    public void deleteOrder(Long orderId) {

        //DB에서 삭제하는 로직이 들어갈 부분 (다음 주차)
        orderRepository.deleteById(orderId);

    }

    @Transactional
    public OrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto) {
        //DB에서 가져오는 로직이 들어갈 부분
        Order order = orderRepository.findById(updateOrderRequestDto.getId()).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        order.update(updateOrderRequestDto);

        //DB에 저장 로직이 들어갈 부분
        orderRepository.save(order);

        return OrderResponseDto.from(order);
    }
}
