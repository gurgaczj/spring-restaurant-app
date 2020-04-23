package com.vandemos.registerservice.repository;

import com.vandemos.registerservice.dao.AddressDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressReposiory extends CrudRepository<AddressDao, Long> {

}
