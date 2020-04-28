package com.vandemos.menuservice.dao;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

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


}
