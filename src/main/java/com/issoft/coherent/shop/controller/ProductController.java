package com.issoft.coherent.shop.controller;

import com.issoft.coherent.shop.document.Product;
import com.issoft.coherent.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/product")
public class ProductController {

    private ProductService productService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Product> addProductToDb(@RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping(path = "/edit/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Product> updateProduct(@PathVariable String productId, @RequestBody Product product) {
        return productService.editProduct(productId, product);
    }

}
