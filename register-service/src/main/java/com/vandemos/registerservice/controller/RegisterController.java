package com.vandemos.registerservice.controller;

import com.vandemos.registerservice.model.Message;
import com.vandemos.registerservice.model.RegisterModel;
import com.vandemos.registerservice.register.RegisterService;
import com.vandemos.registerservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterModel registerModel){
        if(registerService.registerUser(registerModel)) {
            return ResponseEntity.ok("");
        }
        return ResponseEntity.badRequest().body("Coś poszło nie tak, spróbuj później.");
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam(name = "key") Optional<String> key,
                                                 @RequestParam(name = "username") Optional<String> username){
        registerService.confirmAccount(username, key);
        return ResponseEntity.ok().body(new Message("Pomyślnie aktywowano konto ;)"));
    }
}
