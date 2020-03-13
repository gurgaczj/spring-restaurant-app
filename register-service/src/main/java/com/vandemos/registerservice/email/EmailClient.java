package com.vandemos.registerservice.email;

import com.vandemos.registerservice.model.Mail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("email-service")
public interface EmailClient {

    @RequestMapping(method = RequestMethod.POST, value = "/activation", consumes = "application/json")
    void sendActivationMail(Mail mail);

}
