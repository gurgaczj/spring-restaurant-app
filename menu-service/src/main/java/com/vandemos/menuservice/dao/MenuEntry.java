package com.vandemos.menuservice.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
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
    private List<Ingredient> baseIngredients;
    private String type;
    private Map<Ingredient, BigDecimal> additionalIngredients;
}
