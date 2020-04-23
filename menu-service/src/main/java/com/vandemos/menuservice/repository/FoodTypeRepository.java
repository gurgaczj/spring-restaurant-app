package com.vandemos.menuservice.repository;

import com.vandemos.menuservice.dao.FoodType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodTypeRepository extends MongoRepository<FoodType, String> {
}
