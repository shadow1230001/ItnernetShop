package com.issoft.coherent.shop.service;

import com.issoft.coherent.shop.document.User;
import reactor.core.publisher.Mono;

public interface MailService {

    Mono<User> registration(String recipient);

}
