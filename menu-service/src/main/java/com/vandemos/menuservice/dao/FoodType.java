package com.vandemos.menuservice.dao;

import com.vandemos.menuservice.dto.FoodTypeDto;
import com.vandemos.menuservice.dto.IngredientDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@Document(collection = "foodTypes")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class FoodType {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @NonNull
    private String name;

    public FoodTypeDto toDto(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, FoodTypeDto.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodType)) return false;
        FoodType foodType = (FoodType) o;
        return Objects.equals(id, foodType.id) &&
                name.equals(foodType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
