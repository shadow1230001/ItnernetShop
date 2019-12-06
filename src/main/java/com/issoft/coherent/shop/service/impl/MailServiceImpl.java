package com.issoft.coherent.shop.service.impl;

import com.issoft.coherent.shop.service.MailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

import static com.issoft.coherent.shop.model.MailingTemplates.REGISTRATION;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);

    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public Mono<Void> registration(String recipient) {
        return Mono.fromRunnable(() -> {
            Context context = new Context();
            String htmlContent = templateEngine.process(REGISTRATION.getPath(), context);
            sendMail(htmlContent, "Регистрация", recipient);
        }).publishOn(Schedulers.elastic()).then();
    }

    private void sendMail(String template, String subject, String recipient) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setSubject(subject);
            helper.setFrom(sender);
            helper.setText(template, true);
            helper.setTo(recipient);
            mailSender.send(message);
        } catch (MessagingException e) {
            LOG.error("Message not found", e);
        }
    }
}
