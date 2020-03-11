package com.vandemos.registerservice.email;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public boolean sendAccountActivationLink(String email, String firstName, String hash){
        return false;
    }
}
