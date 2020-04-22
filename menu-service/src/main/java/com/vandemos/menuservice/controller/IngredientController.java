package com.vandemos.menuservice.controller;

import com.vandemos.menuservice.dto.IngredientDto;
import com.vandemos.menuservice.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredient/{name}")
    public ResponseEntity<IngredientDto> getByName(@PathVariable String name){
        return ResponseEntity.ok(ingredientService.findByName(name).toDto());
    }
}
