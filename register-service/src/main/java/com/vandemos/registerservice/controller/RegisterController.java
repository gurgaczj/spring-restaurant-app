package com.vandemos.registerservice.controller;

import com.vandemos.registerservice.model.RegisterModel;
import com.vandemos.registerservice.register.RegisterService;
import com.vandemos.registerservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private RegisterService registerService;
    private UserService userService;

    public RegisterController(RegisterService registerService, UserService userService){
        this.registerService = registerService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterModel registerModel){
        if(registerService.registerUser(registerModel)) {
            return ResponseEntity.ok("");
        }
        return ResponseEntity.badRequest().body("Coś poszło nie tak, spróbuj później.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

}
