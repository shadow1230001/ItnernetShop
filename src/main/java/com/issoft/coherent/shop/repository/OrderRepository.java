package com.issoft.coherent.shop.repository;

import com.issoft.coherent.shop.document.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

    Flux<Order> findAllByCompletedIsFalse();
}
