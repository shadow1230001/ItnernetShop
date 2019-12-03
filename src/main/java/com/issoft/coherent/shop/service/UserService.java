package com.issoft.coherent.shop.service;

import com.issoft.coherent.shop.document.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<User> getAll();

    Mono<User> createAdminUser();

}
