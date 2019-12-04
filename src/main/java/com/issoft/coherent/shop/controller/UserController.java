package com.issoft.coherent.shop.controller;

import com.issoft.coherent.shop.document.User;
import com.issoft.coherent.shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/user")
public class UserController {

    private UserService userService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> create() {
        return userService.createAdminUser();
    }

    @PostMapping(path = "/registry", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> registry(@RequestBody String username, @RequestBody String password) {
        return userService.createNewUser(username,password);
    }
}
