package com.vandemos.emailservice.service;

import com.vandemos.emailservice.config.Properties;
import com.vandemos.emailservice.model.Mail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService {

    private final Properties properties;
    private final JavaMailSenderImpl javaMailSender;

    public EmailSenderService(Properties properties, @Qualifier("sender") JavaMailSenderImpl javaMailSender) {
        this.properties = properties;
        this.javaMailSender = javaMailSender;
    }


    public void sendActivationMail(Mail mail) throws MessagingException {
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
    }
}
