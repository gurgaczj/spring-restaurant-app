package com.vandemos.registerservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "registration_info")
@Data
public class RegistrationDao {

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
}
