package com.issoft.coherent.shop.repository;

import com.issoft.coherent.shop.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
