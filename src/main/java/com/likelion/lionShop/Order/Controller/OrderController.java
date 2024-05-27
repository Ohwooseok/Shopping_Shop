package com.likelion.lionShop.Order.Controller;

import com.likelion.lionShop.Order.Dto.request.CreateOrderRequestDto;
import com.likelion.lionShop.Order.Dto.request.UpdateOrderRequestDto;
import com.likelion.lionShop.Order.Dto.response.OrderResponseDto;
import com.likelion.lionShop.Order.Service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order") // uri가 http://~/user로 시작하는 요청을 받습니다.
public class OrderController {

    private final OrderService orderService;

    // 1. 주문을 생성하는 컨트롤러를 만듭니다. 이때 return 값은 "주문 생성하기"입니다. -> 주문은 리스트 형태로 요청을 보내주세요!
    @PostMapping("")
    public ResponseEntity<List<OrderResponseDto>> createOrder(@AuthenticationPrincipal UserDetails userDetails, @RequestBody List<CreateOrderRequestDto> createOrderRequestDtoList){
        List<OrderResponseDto> orderResponseDtos = orderService.createOrders(createOrderRequestDtoList);
        return new ResponseEntity<>(orderResponseDtos, HttpStatus.CREATED);
    }
    // 2. 주문을 가져오는 컨트롤러를 만듭니다. 이때 return 값은 "주문 가져오기"입니다.
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long orderId){
        OrderResponseDto orderResponseDto = orderService.getOrder(orderId);
        return ResponseEntity.ok(orderResponseDto);
    }
    // 3. 주문을 수정하는 컨트롤러를 만듭니다. 이때 return 값은 "주문 수정하기"입니다.
    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateOrderRequestDto updateOrderRequestDto, @PathVariable long orderId ){
        OrderResponseDto orderResponseDto = orderService.updateOrder(updateOrderRequestDto);
        return ResponseEntity.ok(orderResponseDto);

    }
    // 4. 주문을 삭제하는 컨트롤러를 만듭니다. 이때 return 값은 "주문 삭제하기"입니다.
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long orderId, @RequestBody CreateOrderRequestDto createOrderRequestDto){
        orderService.deleteOrder(orderId,createOrderRequestDto);
        return ResponseEntity.noContent().build();
    }

}