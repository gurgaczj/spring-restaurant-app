package com.vandemos.menuservice.repository;

import com.vandemos.menuservice.dao.FoodType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FoodTypeRepository extends MongoRepository<FoodType, String> {

    Optional<FoodType> findByName(String name);
}
