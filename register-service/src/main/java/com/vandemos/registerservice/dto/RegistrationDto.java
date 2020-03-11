package com.vandemos.registerservice.dto;

import com.vandemos.registerservice.dao.AddressDao;
import com.vandemos.registerservice.dao.RegistrationDao;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class RegistrationDto {

    private LocalDateTime registrationDate;
    private String hash;
    private LocalDateTime activationDate;

    public RegistrationDto() {
    }

    public RegistrationDto(LocalDateTime registrationDate, String hash, LocalDateTime activationDate) {
        this.registrationDate = registrationDate;
        this.hash = hash;
        this.activationDate = activationDate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public RegistrationDao toDao() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, RegistrationDao.class);
    }
}
