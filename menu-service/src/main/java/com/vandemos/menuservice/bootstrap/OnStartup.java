package com.vandemos.menuservice.bootstrap;

import com.vandemos.menuservice.dao.Ingredient;
import com.vandemos.menuservice.repository.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class OnStartup implements CommandLineRunner {
    private final IngredientRepository ingredientRepository;

    public OnStartup(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstraping");

        Ingredient ingredient = new Ingredient("Dupa");
        Ingredient ingredient2 = new Ingredient("Dupa2");

        Ingredient save = ingredientRepository.save(ingredient);

        if(save.getName().equals(ingredient.getName())){
            System.out.println("save git");
        }
    }
}
