package me.choi.orderservice.service;

import me.choi.orderservice.dto.OrderDto;
import me.choi.orderservice.jpa.OrderEntity;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);

}
