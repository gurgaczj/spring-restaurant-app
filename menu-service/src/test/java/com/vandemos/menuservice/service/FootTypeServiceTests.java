package com.vandemos.menuservice.service;

import com.vandemos.menuservice.dao.FoodType;
import com.vandemos.menuservice.exception.NotFoundException;
import com.vandemos.menuservice.repository.FoodTypeRepository;
import com.vandemos.menuservice.service.impl.FoodTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class FootTypeServiceTests {

    @Mock
    FoodTypeRepository foodTypeRepository;

    @Test
    public void findByIdTest(){
        final FoodTypeService foodTypeService = new FoodTypeServiceImpl(foodTypeRepository);

        String id = "SOME_ID";

        FoodType foodType = new FoodType(id, "name");

        Mockito.when(foodTypeRepository.findById(id)).thenReturn(Optional.of(foodType));

        FoodType result = foodTypeService.findById(id);

        assertEquals(foodType.getId(), result.getId());
        assertEquals(foodType.getName(), result.getName());
    }

    @Test
    public void findByIdTest_returnEmpty(){
        final FoodTypeService foodTypeService = new FoodTypeServiceImpl(foodTypeRepository);

        String id = "SOME_ID";

        //Mockito.when(foodTypeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> foodTypeService.findById(id));
    }

    @Test
    public void findByNameTest(){
        final FoodTypeService foodTypeService = new FoodTypeServiceImpl(foodTypeRepository);

        String name = "SOME_ID";

        FoodType foodType = new FoodType("id", name);

        Mockito.when(foodTypeRepository.findByName(name)).thenReturn(Optional.of(foodType));

        FoodType result = foodTypeService.findByName(name);

        assertEquals(foodType.getId(), result.getId());
        assertEquals(foodType.getName(), result.getName());
    }

    @Test
    public void findByIdName_returnEmpty(){
        final FoodTypeService foodTypeService = new FoodTypeServiceImpl(foodTypeRepository);

        String name = "name";

        assertThrows(NotFoundException.class, () -> foodTypeService.findById(name));
    }

    @Test
    public void saveTest(){
        final FoodTypeService foodTypeService = new FoodTypeServiceImpl(foodTypeRepository);

        FoodType foodType = new FoodType( "name");
        String id = "SOME_ID";

        Mockito.when(foodTypeRepository.save(foodType)).thenAnswer(invocation -> {
            FoodType argument = (FoodType) invocation.getArguments()[0];
            argument.setId(id);
            return argument;
        });

        FoodType result = foodTypeService.save(foodType);

        assertEquals(foodType.getId(), result.getId());
        assertEquals(foodType.getName(), result.getName());
    }
}
