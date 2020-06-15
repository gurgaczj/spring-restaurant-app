package com.vandemos.registerservice.service;

public interface IAddressService<T, ID> extends BaseService<T, ID>{

    Iterable<T> findAllAddresses();
}
