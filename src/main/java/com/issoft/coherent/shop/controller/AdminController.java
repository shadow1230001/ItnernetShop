package com.issoft.coherent.shop.controller;

import com.issoft.coherent.shop.document.Order;
import com.issoft.coherent.shop.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/admin")
public class AdminController {

    private AdminService adminService;

    @GetMapping(path = "/order/incompleted", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Order> findOrder() {
        return adminService.findInCompletedOrders();
    }
}
