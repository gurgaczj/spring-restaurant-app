package com.vandemos.registerservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "registration_info")
@Data
public class RegistrationDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    @Column
    private String hash;
    @Column(name = "activation_date")
    private LocalDateTime activationDate;

    @OneToOne(mappedBy = "registration")
    @JsonIgnore
    private UserDao user;

    public RegistrationDao(){}
}
