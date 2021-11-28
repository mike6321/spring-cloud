package me.choi.orderservice.controller;

import lombok.AllArgsConstructor;
import me.choi.orderservice.dto.OrderDto;
import me.choi.orderservice.jpa.OrderEntity;
import me.choi.orderservice.service.OrderService;
import me.choi.orderservice.vo.RequestOrder;
import me.choi.orderservice.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/order-service")
public class OrderController {

    private final Environment environment;
    private final OrderService orderService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on Port %s", environment.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder orderDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = modelMapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);
        OrderDto createdOrder = orderService.createOrder(orderDto);


        ResponseOrder responseOrder = modelMapper.map(createdOrder, ResponseOrder.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder orderDetails) {
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(v -> {
            ResponseOrder responseOrder = modelMapper.map(v, ResponseOrder.class);
            result.add(responseOrder);
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
