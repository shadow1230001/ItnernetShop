package com.issoft.coherent.shop.repository;

import com.issoft.coherent.shop.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
