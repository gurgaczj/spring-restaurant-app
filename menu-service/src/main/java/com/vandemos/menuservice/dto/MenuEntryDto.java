package com.vandemos.menuservice.dto;

import com.vandemos.menuservice.dao.Ingredient;
import com.vandemos.menuservice.model.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuEntryDto {

    private FoodTypeDto type;
    private String name;
    private List<Pair<String, List<String>>> params;
    private Map<String, BigDecimal> additionalIngredients;
    private List<IngredientDto> baseIngredients;
    private BigDecimal basePrice;

}
