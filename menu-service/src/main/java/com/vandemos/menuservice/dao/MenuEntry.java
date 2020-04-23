package com.vandemos.menuservice.dao;

import com.vandemos.menuservice.dto.IngredientDto;
import com.vandemos.menuservice.dto.MenuEntryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Document(collection = "menu")
@NoArgsConstructor
@Getter
@Setter
public class MenuEntry {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private BigDecimal basePrice;
    @DBRef
    private List<Ingredient> baseIngredients;
    @DBRef
    private FoodType type;
    private Map<String, BigDecimal> additionalIngredients;


}
