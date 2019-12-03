package com.issoft.coherent.shop.service;

import com.issoft.coherent.shop.model.User;
import reactor.core.publisher.Flux;

public interface UserService {

    Flux<User> getAll();
}
