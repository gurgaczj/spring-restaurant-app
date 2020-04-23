package com.vandemos.registerservice.validator;

public interface ValidatorService {

    void validate(String username, String email, String password, String conformPassword, Integer phoneNumber);
}
