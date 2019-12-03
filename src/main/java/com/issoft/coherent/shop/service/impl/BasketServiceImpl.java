package com.issoft.coherent.shop.service.impl;

import com.issoft.coherent.shop.document.Basket;
import com.issoft.coherent.shop.model.ProductPosition;
import com.issoft.coherent.shop.repository.BasketRepository;
import com.issoft.coherent.shop.repository.ProductRepository;
import com.issoft.coherent.shop.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {

    private BasketRepository basketRepository;

    private ProductRepository productRepository;

    @Override
    public Mono<Basket> add(String productId, int amount, String basketId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Product not found")))
                .flatMap(product -> basketRepository.findById(basketId)
                        .switchIfEmpty(Mono.just(createBasket(basketId)))
                        .map(basket -> {
                            basket.getProductPositions().put(productId, new ProductPosition(product, amount));
                            return basket;
                        }).flatMap(basket -> basketRepository.save(basket)));
    }

    private Basket createBasket(String basketId) {
        Basket basket = new Basket();
        basket.setId(basketId);
        basket.setProductPositions(new HashMap<>());
        basket.setCreated(new Date());
        return basket;
    }
}
