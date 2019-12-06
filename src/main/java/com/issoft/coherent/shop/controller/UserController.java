package com.issoft.coherent.shop.controller;

import com.issoft.coherent.shop.document.User;
import com.issoft.coherent.shop.model.dto.RegistrationForm;
import com.issoft.coherent.shop.service.MailService;
import com.issoft.coherent.shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/user")
public class UserController {

    private UserService userService;
    private MailService mailService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> create() {
        return userService.createAdminUser();
    }

    @PostMapping(path = "/registry", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> registry(@RequestBody RegistrationForm registrationForm) {
        Mono<User> userMono = userService.createNewUser(registrationForm.getUsername(), registrationForm.getPassword());
        return userMono.flatMap(user -> mailService.registration(user.getConfirm(), registrationForm.getUsername()).thenReturn(user));
    }

    @PutMapping(path = "/activity/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> create(@PathVariable String key) {
        return userService.getActivityUser(key);
    }

    @Scheduled(cron = "0 0 12 * * ?", zone = "Europe/Minsk")
    public void updateUser() {
        userService.updateUser().subscribe();
    }
}
