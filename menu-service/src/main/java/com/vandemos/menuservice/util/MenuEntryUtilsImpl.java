package com.vandemos.menuservice.util;

import com.vandemos.menuservice.dao.FoodType;
import com.vandemos.menuservice.dao.Ingredient;
import com.vandemos.menuservice.dao.MenuEntry;
import com.vandemos.menuservice.dto.IngredientDto;
import com.vandemos.menuservice.dto.MenuEntryDto;
import com.vandemos.menuservice.service.IngredientService;
import com.vandemos.menuservice.service.MenuEntryService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MenuEntryUtilsImpl implements MenuEntryUtils{

    private final IngredientService ingredientService;
    private final MenuEntryService menuEntryService;

    public MenuEntryUtilsImpl(IngredientService ingredientService, MenuEntryService menuEntryService) {
        this.ingredientService = ingredientService;
        this.menuEntryService = menuEntryService;
    }

    @Override
    public List<MenuEntryDto> getAllMenuEntries() {
        List<MenuEntryDto> result = new ArrayList<>();

        menuEntryService.findAll().forEach(menuEntry -> {
            result.add(toDto(menuEntry));
        });

        return result;
    }

    public MenuEntryDto toDto(MenuEntry menuEntry){
        ModelMapper modelMapper = new ModelMapper();
        Converter<Map<String, BigDecimal>, Map<String, BigDecimal>> converter = new Converter<Map<String, BigDecimal>, Map<String, BigDecimal>>() {
            @Override
            public Map<String, BigDecimal> convert(MappingContext<Map<String, BigDecimal>, Map<String, BigDecimal>> context) {
                Map<String, BigDecimal> convertResult = new HashMap<>();
                context.getSource().forEach((ingredientId, price) -> {
                    Ingredient ing = ingredientService.findById(ingredientId);
                    convertResult.put(ing.getName(), price);
                });
                return convertResult;
            }
        };
        modelMapper.addConverter(converter);
        return modelMapper.map(menuEntry, MenuEntryDto.class);
    }

}
