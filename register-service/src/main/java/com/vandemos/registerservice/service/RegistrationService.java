package com.vandemos.registerservice.service;

import com.vandemos.registerservice.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }
}
