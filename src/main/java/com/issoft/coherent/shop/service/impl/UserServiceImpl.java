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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
        User user = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("1"))
                .active(true)
                .roles(List.of(Role.ADMIN))
                .build();
        return userRepository.save(user);
    }

    @Override
    public Mono<User> createNewUser(String email, String password) {
        return userRepository.findByUsername(email)
                .switchIfEmpty(Mono.just(new User()))
                .map(user -> {
                    if (user.getId() == null) {
                        return User.builder()
                                .username(email.toLowerCase())
                                .password(passwordEncoder.encode(password))
                                .statuses(Status.NEW_USER)
                                .confirm(UUID.randomUUID().toString())
                                .build();
                    } else {
                        throw new IllegalArgumentException("User not found");
                    }
                }).flatMap(userRepository::save);
    }

    @Override
    public Mono<User> getActivityUser(String key) {
        return userRepository.findByConfirm(key).map(user -> {
            user.setStatuses(Status.ACTIVE);
            return user;
        }).flatMap(userRepository::save);
    }

    @Override
    public Mono<Void> updateUser() {
        return userRepository.deleteUserByStatusesAndCreatedLessThan(Status.NEW_USER, getRightDay());
    }

    private LocalDateTime getRightDay() {
        LocalDateTime today = LocalDateTime.now();
        return today.minusDays(1);
    }

}
