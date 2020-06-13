package com.vandemos.emailservice.controller;

import com.vandemos.emailservice.model.Mail;
import com.vandemos.emailservice.service.EmailSenderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;

@RestController
public class EmailController {

    private final EmailSenderServiceImpl emailSenderService;

    public EmailController(EmailSenderServiceImpl emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    /**
     * Dunno why I get request timed out while calling this endpoint. That's why there is void.
     * @param mail
     */
    @PostMapping("/activation")
    public ResponseEntity<Mail> activateAccount(@RequestBody Mail mail) {
        try {
            return ResponseEntity.ok(emailSenderService.sendActivationMail(mail));
        } catch (MessagingException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error while sending mail", e);
        }

    }

}