package com.issoft.coherent.shop.service;

import com.issoft.coherent.shop.model.Basket;
import reactor.core.publisher.Mono;

public interface BasketService {
    Mono<Basket> add(String productId, int amount, String basketId);
}
