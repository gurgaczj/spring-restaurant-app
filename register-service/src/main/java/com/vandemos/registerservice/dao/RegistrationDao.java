package com.vandemos.registerservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vandemos.registerservice.dto.RegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "registration_info")
public class RegistrationDao implements Dtoable<RegistrationDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    @NotNull
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    @NotNull
    @Column
    private String hash;
    @Nullable
    @Column(name = "activation_date")
    private LocalDateTime activationDate;

    @OneToOne(mappedBy = "registration")
    @JsonIgnore
    private UserDao user;

    public RegistrationDao(){}

    @Override
    public RegistrationDto toDto() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, RegistrationDto.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Nullable
    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(@Nullable LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public UserDao getUser() {
        return user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }
}
