package com.vandemos.registerservice.dto;

import com.vandemos.registerservice.dao.AddressDao;
import com.vandemos.registerservice.dao.UserInfoDao;
import org.modelmapper.ModelMapper;

public class UserInfoDto {

    private String firstName;
    private String lastName;
    private Integer phoneNumber;
    private AddressDto address;

    public UserInfoDto(){}

    public UserInfoDto(String firstName, String lastName, Integer phoneNumber, AddressDto address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public UserInfoDao toDao() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, UserInfoDao.class);
    }
}
