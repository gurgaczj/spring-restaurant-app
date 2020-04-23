package com.vandemos.emailservice.service;

import com.vandemos.emailservice.model.Mail;

import javax.mail.MessagingException;

public interface EmailSenderService {

    Mail sendActivationMail(Mail mail) throws MessagingException;
}
