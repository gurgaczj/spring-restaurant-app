package com.vandemos.registerservice.service;

public interface IUserInfoService<T, ID> extends BaseService<T, ID> {

    Iterable<T> findAll();
}
