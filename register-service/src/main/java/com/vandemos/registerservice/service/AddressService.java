package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.Address;
import com.vandemos.registerservice.repository.AddressReposiory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class AddressService implements IAddressService<Address, Long>{

    private final AddressReposiory addressReposiory;

    public AddressService(AddressReposiory addressReposiory) {
        this.addressReposiory = addressReposiory;
    }

    public Iterable<Address> findAllAddresses() {
        return addressReposiory.findAll();
    }

    public Address findById(Long id) {
        //TODO: throw exception
        return addressReposiory.findById(id)
                .orElseThrow(() -> new AddressNotFound("Address with id " + id + " was not found"));
    }

    public Address save(Address address) {
        return addressReposiory.save(address);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    final private static class AddressNotFound extends RuntimeException{

        public AddressNotFound(String message) {
            super(message);
        }
    }
}
