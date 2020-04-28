package com.vandemos.menuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class IngredientDto {

    private String name;


    public IngredientDto setName(String name){
        this.name = name;
        return this;
    }
}