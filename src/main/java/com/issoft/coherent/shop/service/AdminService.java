package com.issoft.coherent.shop.service;

import com.issoft.coherent.shop.document.Order;
import reactor.core.publisher.Flux;

public interface AdminService {

    Flux<Order> findInCompletedOrders();

}
