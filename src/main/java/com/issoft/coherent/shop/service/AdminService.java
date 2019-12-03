package com.issoft.coherent.shop.service;

import com.issoft.coherent.shop.document.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AdminService {

    Flux<Order> findInCompletedOrders();

    Mono<Order> completeOrder(String orderId);
}
