package com.issoft.coherent.shop.controller;

import com.issoft.coherent.shop.document.Order;
import com.issoft.coherent.shop.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/admin")
public class AdminController {

    private AdminService adminService;

    @GetMapping(path = "/order/incompleted", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Order> findOrder() {
        return adminService.findInCompletedOrders();
    }

    @PutMapping(path = "/order/{orderId}/complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Order> completeOrder(@PathVariable String orderId) {
        return adminService.completeOrder(orderId);
    }
}
