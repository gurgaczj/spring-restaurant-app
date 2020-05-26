package com.vandemos.menuservice.dao;

import com.vandemos.menuservice.dto.IngredientDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@Document(collection = "ingredient")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Ingredient {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @NonNull
    private String name;

    public IngredientDto toDto(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, IngredientDto.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
