package com.vandemos.registerservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity(name = "address")
@Data
public class AddressDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    @NotNull
    private String street;
    @NotNull
    @Column(name = "house_number")
    private Short houseNumber;
    @Nullable
    @Column(name = "apartment_number", nullable = true)
    private Short aparmentNumber;
    @Nullable
    @Column(name = "flat_number", nullable = true)
    private Short flatNumber;
    @NotNull
    @Column(name = "postal_code")
    private String postalCode;
    @NotNull
    private String city;

    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER)
    @JsonIgnore
    private UserInfoDao user;

    public AddressDao() {
    }


}
