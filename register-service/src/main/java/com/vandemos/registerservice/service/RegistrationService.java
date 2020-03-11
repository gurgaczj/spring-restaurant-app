package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.RegistrationDao;
import com.vandemos.registerservice.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Optional<RegistrationDao> save(RegistrationDao registrationDao) {
        return Optional.of(registrationRepository.save(registrationDao));
    }
}
