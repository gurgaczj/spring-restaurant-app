package com.vandemos.menuservice.service;

import com.vandemos.menuservice.dao.Ingredient;
import com.vandemos.menuservice.dto.IngredientDto;

public interface IngredientService {

    Ingredient findById(String id);
    Ingredient findByName(String name);
}
