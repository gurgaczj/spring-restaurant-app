package com.vandemos.emailservice.controller;

import com.vandemos.emailservice.model.Mail;
import com.vandemos.emailservice.service.EmailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailController {

    private final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    /**
     * Dunno why I get request timed out while calling this endpoint. That's why there is void.
     * @param mail
     */
    @PostMapping("/activation")
    public void activateAccount(@RequestBody Mail mail) {
        try {
            emailSenderService.sendActivationMail(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}