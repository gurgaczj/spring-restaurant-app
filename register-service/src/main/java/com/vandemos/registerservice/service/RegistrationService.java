package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.RegistrationDao;
import com.vandemos.registerservice.repository.RegistrationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Service
public class RegistrationService implements IRegistrationService<RegistrationDao, Long> {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public RegistrationDao findById(Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(() -> new RegistrationNotFound("Registration with id " + id + " was not found"));
    }

    public RegistrationDao save(RegistrationDao registrationDao) {
        return registrationRepository.save(registrationDao);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    final private static class RegistrationNotFound extends RuntimeException {

        public RegistrationNotFound(String message) {
            super(message);
        }
    }
}
