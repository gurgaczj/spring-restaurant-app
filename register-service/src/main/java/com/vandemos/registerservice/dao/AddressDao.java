package com.vandemos.registerservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "address")
@Data
public class AddressDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    private String street;

    @Column(name = "house_number")
    private Short houseNumber;
    @Column(name = "apartment_number", nullable = true)
    private Short aparmentNumber;
    @Column(name = "flat_number", nullable = true)
    private Short flatNumber;
    @Column(name = "postal_code")
    private String postalCode;
    private String city;

    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER)
    @JsonIgnore
    private UserInfoDao user;

    public AddressDao() {
    }


}
