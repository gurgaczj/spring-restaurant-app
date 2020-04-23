package com.vandemos.menuservice.bootstrap;

import com.vandemos.menuservice.dao.FoodType;
import com.vandemos.menuservice.dao.Ingredient;
import com.vandemos.menuservice.dao.MenuEntry;
import com.vandemos.menuservice.repository.FoodTypeRepository;
import com.vandemos.menuservice.repository.IngredientRepository;
import com.vandemos.menuservice.repository.MenuEntryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//@Component
public class OnStartup implements CommandLineRunner {
    private final IngredientRepository ingredientRepository;
    private final FoodTypeRepository foodTypeRepository;
    private final MenuEntryRepository menuEntryRepository;

    public OnStartup(IngredientRepository ingredientRepository, FoodTypeRepository foodTypeRepository, MenuEntryRepository menuEntryRepository) {
        this.ingredientRepository = ingredientRepository;
        this.foodTypeRepository = foodTypeRepository;
        this.menuEntryRepository = menuEntryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstraping");

        Ingredient ingredient = new Ingredient("Dupa");
        Ingredient ingredient2 = new Ingredient("Dupa2");


        Ingredient save = ingredientRepository.findFirstByName("Dupa").orElseThrow();
        Ingredient save2 = ingredientRepository.findFirstByName("Dupa2").orElseThrow();

        FoodType pizza = new FoodType();
        pizza.setName("pizza");

        pizza = foodTypeRepository.save(pizza);


        MenuEntry menuEntry1 = new MenuEntry();
        menuEntry1.setType(pizza);
        menuEntry1.setBasePrice(new BigDecimal("25.54"));
        menuEntry1.setBaseIngredients(Arrays.asList(save));
        Map<String, BigDecimal> additional = new HashMap();
        additional.put(save2.getId(), new BigDecimal("10.24"));
        menuEntry1.setAdditionalIngredients(additional);

        MenuEntry e = menuEntryRepository.save(menuEntry1);
        e.getBaseIngredients().forEach(ingredient1 -> System.out.println(ingredient.getName()));

        additional.forEach((s, bigDecimal) -> {
            Ingredient i = ingredientRepository.findById(s).orElseThrow();
            System.out.println(i.getName());
        });
    }
}
