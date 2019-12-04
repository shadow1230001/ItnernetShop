package com.issoft.coherent.shop.service.impl;

import com.issoft.coherent.shop.document.Order;
import com.issoft.coherent.shop.model.OrderForm;
import com.issoft.coherent.shop.repository.BasketRepository;
import com.issoft.coherent.shop.repository.OrderRepository;
import com.issoft.coherent.shop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private BasketRepository basketRepository;

    @Override
    public Mono<Order> placeOrder(OrderForm orderForm, String basketId) {
        return basketRepository.findById(basketId).switchIfEmpty(Mono.error(new IllegalArgumentException("Basket not found")))
                .map(basket -> {
                    double total = basket.getProductPositions().values().stream()
                            .mapToDouble(productPosition -> productPosition.getProduct().getPrice() * productPosition.getAmount())
                            .sum();
                    return Order.builder().total(total).orderForm(orderForm).created(new Date()).productPositions(basket.getProductPositions().values()).build();
                }).flatMap(orderRepository::save);
    }

    @Override
    public Flux<Order> findInCompletedOrders() {
        return orderRepository.findAllByCompletedIsFalse();
    }

    @Override
    public Mono<Order> completeOrder(String orderId) {
        return orderRepository.findById(orderId).switchIfEmpty(Mono.error(new IllegalArgumentException("Order not found")))
                .map(order -> {
                    order.setCompleted(true);
                    return order;
                }).flatMap(orderRepository::save);
    }
}
