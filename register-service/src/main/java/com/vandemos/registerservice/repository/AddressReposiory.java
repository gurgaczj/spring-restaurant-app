package com.vandemos.registerservice.repository;

import com.vandemos.registerservice.dao.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressReposiory extends CrudRepository<Address, Long> {

}
