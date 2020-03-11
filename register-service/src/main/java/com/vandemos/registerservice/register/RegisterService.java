package com.vandemos.registerservice.register;

import com.vandemos.registerservice.exception.RegisterException;
import com.vandemos.registerservice.model.RegisterModel;
import com.vandemos.registerservice.validator.ValidatorService;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private ValidatorService validatorService;

    public RegisterService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    public void registerUser(RegisterModel registerModel){
        validatorService.validate(registerModel.getUsername(), registerModel.getEmail(), registerModel.getPassword(),
                registerModel.getConfirmPassword(), registerModel.getUser().getPhoneNumber());


    }

}
