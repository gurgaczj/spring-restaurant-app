package com.vandemos.registerservice.controller;

import com.vandemos.registerservice.model.RegisterModel;
import com.vandemos.registerservice.register.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private RegisterService registerService;

    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(RegisterModel registerModel){
        return ResponseEntity.ok("");
    }

}
