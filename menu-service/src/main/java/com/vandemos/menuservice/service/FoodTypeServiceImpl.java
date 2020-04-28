package com.vandemos.menuservice.service;

import com.vandemos.menuservice.dao.FoodType;
import com.vandemos.menuservice.exception.NotFoundException;
import com.vandemos.menuservice.repository.FoodTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {

    private final FoodTypeRepository foodTypeRepository;

    public FoodTypeServiceImpl(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }

    @Override
    public FoodType findById(String id) {
        return foodTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Food type with id " + id + " was not found"));
    }

    @Override
    public FoodType findByName(String name) {
        return foodTypeRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Food type with name " + name + " was not found"));
    }

    @Override
    public FoodType save(FoodType foodType) {
        return foodTypeRepository.save(foodType);
    }
}
