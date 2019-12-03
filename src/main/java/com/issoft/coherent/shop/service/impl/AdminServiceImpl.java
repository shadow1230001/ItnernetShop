package com.issoft.coherent.shop.service.impl;

import com.issoft.coherent.shop.document.Order;
import com.issoft.coherent.shop.repository.OrderRepository;
import com.issoft.coherent.shop.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private OrderRepository orderRepository;

    @Override
    public Flux<Order> findInCompletedOrders() {
        return orderRepository.findAllByCompletedIsFalse();
    }
}
