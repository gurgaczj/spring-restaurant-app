package com.vandemos.menuservice.repository;

import com.vandemos.menuservice.dao.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends MongoRepository<Ingredient, String> {

    Optional<Ingredient> findFirstByName(String name);
}
