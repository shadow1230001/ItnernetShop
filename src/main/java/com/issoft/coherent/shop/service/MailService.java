package com.issoft.coherent.shop.service;

import reactor.core.publisher.Mono;

public interface MailService {

    Mono<Void> registration(String key, String recipient);

}
