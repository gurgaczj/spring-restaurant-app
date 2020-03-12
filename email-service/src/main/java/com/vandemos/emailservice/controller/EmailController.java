package com.vandemos.emailservice.controller;

import com.vandemos.emailservice.model.Mail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @PostMapping("/activation")
    public ResponseEntity<?> activateAccount(@RequestBody Mail mail) {
        return ResponseEntity.ok("");
    }
}
