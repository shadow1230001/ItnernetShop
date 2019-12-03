package com.issoft.coherent.shop.repository;

import com.issoft.coherent.shop.model.Basket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BasketRepository extends ReactiveMongoRepository<Basket, String> {
}
