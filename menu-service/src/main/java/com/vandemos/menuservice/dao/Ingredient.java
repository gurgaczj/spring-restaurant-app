package com.vandemos.menuservice.dao;

import com.vandemos.menuservice.dto.IngredientDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "ingredient")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Ingredient {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String name;

    public Ingredient(String name){
        this.name = name;
    }

    public IngredientDto toDto(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, IngredientDto.class);
    }
}
