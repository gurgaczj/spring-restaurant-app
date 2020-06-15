package com.vandemos.emailservice.service;

import com.vandemos.emailservice.config.Properties;
import com.vandemos.emailservice.model.Mail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private final Properties properties;
    private final JavaMailSender javaMailSender;

    public EmailSenderServiceImpl(Properties properties, @Qualifier("sender") JavaMailSender javaMailSender) {
        this.properties = properties;
        this.javaMailSender = javaMailSender;
    }


    public Mail sendActivationMail(Mail mail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setTo(mail.getEmail());
        messageHelper.setSubject("Potwierdź rejestrację w " + properties.getRestaurantName());
        StringBuilder stringBuilder = new StringBuilder("Witaj " + mail.getFirstName() + "\n\n");
        stringBuilder.append("Potwierdz rejestarację klikając w poniższy link\n");
        stringBuilder.append("http://localhost:8081/confirm?key=");
        stringBuilder.append(mail.getHash());
        stringBuilder.append("&username=");
        stringBuilder.append(mail.getUsername());

        System.out.println(stringBuilder.toString());

        messageHelper.setText(stringBuilder.toString());

        javaMailSender.send(message);

        return mail;
    }
}
