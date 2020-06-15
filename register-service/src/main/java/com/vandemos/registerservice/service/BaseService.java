package com.vandemos.registerservice.service;

public interface BaseService<T, ID> {

    T findById(ID id);

    T save(T entity);
}
