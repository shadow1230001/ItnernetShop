package com.issoft.coherent.shop.service.impl;

import com.issoft.coherent.shop.document.Product;
import com.issoft.coherent.shop.repository.ProductRepository;
import com.issoft.coherent.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Flux<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Mono<Product> editProduct(String productId, Product product) {
        return productRepository.findById(productId).switchIfEmpty(Mono.error(new IllegalArgumentException("Product not found")))
                .map(product1 -> Product.builder()
                        .description(product.getDescription())
                        .name(product.getName())
                        .price(product.getPrice())
                        .build()).flatMap(productRepository::save);
    }
}
