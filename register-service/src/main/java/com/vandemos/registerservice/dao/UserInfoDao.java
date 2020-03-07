package com.vandemos.registerservice.dao;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "user_info")
@Data
public class UserInfoDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressDao address;

    public UserInfoDao() {
    }
}
