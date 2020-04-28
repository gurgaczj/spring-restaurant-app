package com.vandemos.menuservice.service;

import com.vandemos.menuservice.dao.Ingredient;
import com.vandemos.menuservice.exception.NotFoundException;
import com.vandemos.menuservice.repository.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient findByName(String name){
        return ingredientRepository.findFirstByName(name)
                .orElseThrow(() -> new NotFoundException("Ingredient with name " + name + " was not found."));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient findById(String id){
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient with id " + id + " was not found"));
    }
}
