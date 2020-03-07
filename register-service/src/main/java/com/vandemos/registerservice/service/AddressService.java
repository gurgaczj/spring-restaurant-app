package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.AddressDao;
import com.vandemos.registerservice.repository.AddressReposiory;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private AddressReposiory addressReposiory;

    public AddressService(AddressReposiory addressReposiory) {
        this.addressReposiory = addressReposiory;
    }

    public Iterable<AddressDao> getAllAddresses(){
        return addressReposiory.findAll();
    }

    public AddressDao getByAddressById(Long id){
        //TODO: throw exception
        return addressReposiory.findById(id).orElseThrow();
    }
}
