package com.issoft.coherent.shop.service.impl;

import com.issoft.coherent.shop.document.User;
import com.issoft.coherent.shop.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import static com.issoft.coherent.shop.model.MailingTemplates.REGISTRATION;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final static Logger LOGGER = Logger.getLogger(MailServiceImpl.class.getName());

    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public Mono<User> registration(String recipient) {
        return Mono.fromRunnable(() -> {
            Context context = new Context();
            String htmlContent = templateEngine.process(REGISTRATION.getPath(), context);
            sendMail(htmlContent, "Регистрация", recipient);
        });
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
            LOGGER.info("Message dont sent");
        }
    }
}
