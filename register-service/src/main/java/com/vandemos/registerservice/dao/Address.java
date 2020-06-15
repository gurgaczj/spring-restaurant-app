package com.vandemos.registerservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vandemos.registerservice.dto.AddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "address")
public class Address {

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

    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private UserInfo user;

    public Address() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Short getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Short houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Nullable
    public Short getAparmentNumber() {
        return aparmentNumber;
    }

    public void setAparmentNumber(@Nullable Short aparmentNumber) {
        this.aparmentNumber = aparmentNumber;
    }

    @Nullable
    public Short getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(@Nullable Short flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public AddressDto toDto() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, AddressDto.class);
    }
}
