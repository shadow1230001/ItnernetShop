package com.issoft.coherent.shop.controller;

import com.issoft.coherent.shop.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/mail")
public class MailController {

    private MailService mailService;

    @PostMapping(path = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> registration(String link, String recipient) {
        return mailService.registration(link, recipient);
    }
}
