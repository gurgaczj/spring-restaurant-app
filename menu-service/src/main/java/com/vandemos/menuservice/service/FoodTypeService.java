package com.vandemos.menuservice.service;

import com.vandemos.menuservice.dao.FoodType;

public interface FoodTypeService {

    FoodType findById(String id);
    FoodType findByName(String name);
    FoodType save(FoodType foodType);
}
