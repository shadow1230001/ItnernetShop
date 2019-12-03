package com.issoft.coherent.shop.controller;

import com.issoft.coherent.shop.document.Order;
import com.issoft.coherent.shop.model.OrderForm;
import com.issoft.coherent.shop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/order")
public class OrderController {

    private OrderService orderService;

    @PostMapping(path = "/place", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Order> place(@RequestBody OrderForm orderForm, WebSession session) {
        return orderService.placeOrder(orderForm, session.getId());
    }
}
