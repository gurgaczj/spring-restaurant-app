package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.AddressDao;

public interface IAddressService<T, ID> extends BaseService<T, ID>{

    Iterable<T> findAllAddresses();
}
