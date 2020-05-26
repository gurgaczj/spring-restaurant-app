package com.vandemos.menuservice.util.impl;

import com.vandemos.menuservice.dao.FoodType;
import com.vandemos.menuservice.dao.Ingredient;
import com.vandemos.menuservice.dao.MenuEntry;
import com.vandemos.menuservice.dto.FoodTypeDto;
import com.vandemos.menuservice.dto.IngredientDto;
import com.vandemos.menuservice.dto.MenuEntryDto;
import com.vandemos.menuservice.exception.NotFoundException;
import com.vandemos.menuservice.service.FoodTypeService;
import com.vandemos.menuservice.service.IngredientService;
import com.vandemos.menuservice.util.MenuEntryUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class MenuEntryUtilImpl implements MenuEntryUtil {

    private final IngredientService ingredientService;
    private final FoodTypeService foodTypeService;

    public MenuEntryUtilImpl(IngredientService ingredientService, FoodTypeService foodTypeService) {
        this.ingredientService = ingredientService;
        this.foodTypeService = foodTypeService;
    }

    @Override
    public MenuEntryDto toDto(MenuEntry menuEntry) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(menuEntry, MenuEntryDto.class);
    }

    @Override
    public MenuEntry toDao(MenuEntryDto menuEntryDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(this::convertIngredient, IngredientDto.class, Ingredient.class);
        modelMapper.addConverter(this::convertFoodType, FoodTypeDto.class, FoodType.class);
        return modelMapper.map(menuEntryDto, MenuEntry.class);
    }

    private FoodType convertFoodType(MappingContext<FoodTypeDto, FoodType> context) {
        String foodTypeName = context.getSource().getName();
        try {
            return foodTypeService.findByName(foodTypeName);
        } catch (NotFoundException e) {
            return foodTypeService.save(new FoodType(foodTypeName));
        }
    }

    private Ingredient convertIngredient(MappingContext<IngredientDto, Ingredient> context) {
        String ingredientName = context.getSource().getName();
        try {
            return ingredientService.findByName(ingredientName);
        } catch (NotFoundException e) {
            return ingredientService.save(new Ingredient(ingredientName));
        }
    }
}
