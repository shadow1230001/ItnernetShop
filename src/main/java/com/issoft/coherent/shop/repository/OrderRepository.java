package com.issoft.coherent.shop.repository;

import com.issoft.coherent.shop.document.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
}
