package com.issoft.coherent.shop.controller;

import com.issoft.coherent.shop.model.Basket;
import com.issoft.coherent.shop.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/basket")
public class BasketController {

    private BasketService basketService;

    @GetMapping(path = "/add/{productId}/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Basket> add(@PathVariable String productId, @PathVariable int amount, WebSession webSession) {

        return basketService.add(productId, amount, webSession.getId());
    }
}
