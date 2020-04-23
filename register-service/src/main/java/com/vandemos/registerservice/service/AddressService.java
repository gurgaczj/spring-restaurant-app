package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.AddressDao;
import com.vandemos.registerservice.repository.AddressReposiory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Service
public class AddressService implements IAddressService<AddressDao, Long>{

    private final AddressReposiory addressReposiory;

    public AddressService(AddressReposiory addressReposiory) {
        this.addressReposiory = addressReposiory;
    }

    public Iterable<AddressDao> findAllAddresses() {
        return addressReposiory.findAll();
    }

    public AddressDao findById(Long id) {
        //TODO: throw exception
        return addressReposiory.findById(id)
                .orElseThrow(() -> new AddressNotFound("Address with id " + id + " was not found"));
    }

    public AddressDao save(AddressDao addressDao) {
        return addressReposiory.save(addressDao);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    final private static class AddressNotFound extends RuntimeException{

        public AddressNotFound(String message) {
            super(message);
        }
    }
}
