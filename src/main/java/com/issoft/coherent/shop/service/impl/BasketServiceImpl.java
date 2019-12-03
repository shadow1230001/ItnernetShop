package com.issoft.coherent.shop.service.impl;

import com.issoft.coherent.shop.model.Basket;
import com.issoft.coherent.shop.model.ProductPosition;
import com.issoft.coherent.shop.repository.BasketRepository;
import com.issoft.coherent.shop.repository.ProductRepository;
import com.issoft.coherent.shop.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {

    private BasketRepository basketRepository;

    private ProductRepository productRepository;

    @Override
    public Mono<Basket> add(String productId, int amount, String basketId) {

        return productRepository.findById(productId).map(product -> {
            Basket basket = new Basket();
            basket.setId(basketId);
            ProductPosition productPosition = new ProductPosition(product, amount);
            basket.setProductPositions(List.of(productPosition));
            return basket;
        }).flatMap(basket -> basketRepository.save(basket));

    }
}
