package com.issoft.coherent.shop.service.impl;

import com.issoft.coherent.shop.document.User;
import com.issoft.coherent.shop.model.Role;
import com.issoft.coherent.shop.model.Status;
import com.issoft.coherent.shop.repository.UserRepository;
import com.issoft.coherent.shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public Flux<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> createAdminUser() {
        User user = User.builder().username("admin").password(passwordEncoder.encode("1")).active(true).created(new Date()).roles(List.of(Role.ADMIN)).build();
        return userRepository.save(user);
    }

    @Override
    public Mono<User> createNewUser(String email, String password) {
        return userRepository.findByUsername(email).switchIfEmpty(Mono.error(new IllegalArgumentException("This username already exists"))).map(user -> {
            User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .id(user.getId())
                    .statuses(Status.NEW_USER)
                    .build();
            return user;
        }).flatMap(userRepository::save);
    }

}
