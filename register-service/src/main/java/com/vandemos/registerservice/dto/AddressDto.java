package com.vandemos.registerservice.dto;

import com.vandemos.registerservice.dao.AddressDao;
import org.modelmapper.ModelMapper;

public class AddressDto {

    private String street;
    private Short houseNumber;
    private Short aparmentNumber;
    private Short flatNumber;
    private String postalCode;
    private String city;

    public AddressDto(){}

    public AddressDto(String street, Short houseNumber, Short aparmentNumber, Short flatNumber, String postalCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.aparmentNumber = aparmentNumber;
        this.flatNumber = flatNumber;
        this.postalCode = postalCode;
        this.city = city;
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

    public Short getAparmentNumber() {
        return aparmentNumber;
    }

    public void setAparmentNumber(Short aparmentNumber) {
        this.aparmentNumber = aparmentNumber;
    }

    public Short getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Short flatNumber) {
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

    public AddressDao toDao() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, AddressDao.class);
    }
}
