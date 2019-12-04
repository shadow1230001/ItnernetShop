package com.issoft.coherent.shop.service;

import com.issoft.coherent.shop.document.Order;
import com.issoft.coherent.shop.model.OrderForm;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Order> placeOrder(OrderForm orderForm, String basketId);

    Flux<Order> findInCompletedOrders();

    Mono<Order> completeOrder(String orderId);
}
