package com.issoft.coherent.shop.repository;

import com.issoft.coherent.shop.document.User;
import com.issoft.coherent.shop.model.Status;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByUsername(String username);

    Mono<Void> deleteUserByStatusesAndCreatedLessThan(Status status, LocalDateTime created);

    Mono<User> findByConfirm(String confirm);

}
