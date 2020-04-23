package com.vandemos.menuservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "foodTypes")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class FoodType {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String name;
}
