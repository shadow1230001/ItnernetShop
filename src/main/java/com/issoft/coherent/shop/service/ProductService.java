package com.issoft.coherent.shop.service;

import com.issoft.coherent.shop.document.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> getAll();

    Mono<Product> save(Product product);
}
