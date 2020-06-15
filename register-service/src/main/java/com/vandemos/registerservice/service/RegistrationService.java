package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.Registration;
import com.vandemos.registerservice.repository.RegistrationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class RegistrationService implements IRegistrationService<Registration, Long> {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public Registration findById(Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(() -> new RegistrationNotFound("Registration with id " + id + " was not found"));
    }

    public Registration save(Registration registration) {
        return registrationRepository.save(registration);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    final private static class RegistrationNotFound extends RuntimeException {

        public RegistrationNotFound(String message) {
            super(message);
        }
    }
}
