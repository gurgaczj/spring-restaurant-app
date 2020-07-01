package com.vandemos.authenticationservice.forgotpassword;

import com.vandemos.authenticationservice.model.UserNewPassword;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody UserNewPassword newPassword){

    }

}
