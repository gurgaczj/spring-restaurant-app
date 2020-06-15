package com.vandemos.registerservice.service;

public interface IUserService<T, ID> extends BaseService<T, ID> {

    T findByUsername(String username);
    T findByEmail(String email);
}
