package com.vandemos.menuservice.dao;

import com.vandemos.menuservice.dto.IngredientDto;
import com.vandemos.menuservice.dto.MenuEntryDto;
import com.vandemos.menuservice.model.Pair;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Document(collection = "menu")
@NoArgsConstructor
@Getter
@Setter
public class MenuEntry {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @Indexed(unique = true)
    private String name;
    private BigDecimal basePrice;
    private List<Pair<String, List<String>>> params;
    @DBRef
    private List<Ingredient> baseIngredients;
    @DBRef
    private FoodType type;
    private Map<String, BigDecimal> additionalIngredients;
}
