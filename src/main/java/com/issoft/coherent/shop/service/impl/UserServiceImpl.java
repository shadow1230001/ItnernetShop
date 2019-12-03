package com.issoft.coherent.shop.service.impl;

import com.issoft.coherent.shop.document.User;
import com.issoft.coherent.shop.repository.UserRepository;
import com.issoft.coherent.shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public Flux<User> getAll() {
        return userRepository.findAll();
    }
}
