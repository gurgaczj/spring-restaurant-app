package com.vandemos.menuservice.util;

import com.vandemos.menuservice.dao.FoodType;
import com.vandemos.menuservice.dao.Ingredient;
import com.vandemos.menuservice.dao.MenuEntry;
import com.vandemos.menuservice.dto.MenuEntryDto;
import com.vandemos.menuservice.exception.NotFoundException;
import com.vandemos.menuservice.model.Pair;
import com.vandemos.menuservice.service.FoodTypeService;
import com.vandemos.menuservice.service.IngredientService;
import com.vandemos.menuservice.service.impl.FoodTypeServiceImpl;
import com.vandemos.menuservice.service.impl.IngredientServiceImpl;
import com.vandemos.menuservice.util.impl.MenuEntryUtilImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MenuEntryUtilTests {

    private MenuEntry menuEntryDao;
    private MenuEntryDto menuEntryDto;

    @Before
    public void testsInit() {
        menuEntryDao = new MenuEntry();

        Map<String, BigDecimal> additionalIngredients = new HashMap<>();
        additionalIngredients.put("additional ingredient 1", new BigDecimal("12.45"));
        menuEntryDao.setAdditionalIngredients(additionalIngredients);

        FoodType foodType = new FoodType("food type 1");
        foodType.setId("ID1");
        menuEntryDao.setType(foodType);

        Ingredient ingredient = new Ingredient("ingredient 1");
        ingredient.setId("ID2");
        menuEntryDao.setBaseIngredients(Collections.singletonList(ingredient));

        List<Pair<String, List<String>>> parameters = new ArrayList<>();
        Pair<String, List<String>> pair = new Pair<>("Param key", Arrays.asList("val1", "val2"));
        parameters.add(pair);
        menuEntryDao.setParams(parameters);

        menuEntryDao.setBasePrice(new BigDecimal("21.99"));

        menuEntryDao.setName("dao name");

        menuEntryDao.setId("ID1");

        menuEntryDto = new MenuEntryDto();
        menuEntryDto.setAdditionalIngredients(additionalIngredients);
        menuEntryDto.setBaseIngredients(Collections.singletonList(ingredient.toDto()));
        menuEntryDto.setParams(parameters);
        menuEntryDto.setName("dto name");
        menuEntryDto.setBasePrice(new BigDecimal("19.99"));
        menuEntryDto.setType(foodType.toDto());
    }

    @Test
    public void toDtoTest() {
        final IngredientService ingredientService = Mockito.mock(IngredientServiceImpl.class);
        final FoodTypeService foodTypeService = Mockito.mock(FoodTypeServiceImpl.class);

        final MenuEntryUtil menuEntryUtil = new MenuEntryUtilImpl(ingredientService, foodTypeService);

        MenuEntryDto dto = menuEntryUtil.toDto(menuEntryDao);

        assertEquals(dto.getName(), menuEntryDao.getName());
        assertEquals(dto.getBasePrice(), menuEntryDao.getBasePrice());
        assertEquals(dto.getType().getName(), menuEntryDao.getType().getName());
        assertEquals(dto.getParams(), menuEntryDao.getParams());
        dto.getBaseIngredients().forEach(ingredientDto -> {
            assertTrue(menuEntryDao.getBaseIngredients().stream()
                    .anyMatch(ingredient -> ingredient.getName().equals(ingredientDto.getName())));
        });
        assertEquals(dto.getAdditionalIngredients(), menuEntryDao.getAdditionalIngredients());
    }

    @Test
    public void toDaoTest(){
        final IngredientService ingredientService = Mockito.mock(IngredientServiceImpl.class);
        final FoodTypeService foodTypeService = Mockito.mock(FoodTypeServiceImpl.class);

        final MenuEntryUtil menuEntryUtil = new MenuEntryUtilImpl(ingredientService, foodTypeService);

        FoodType foodType = new FoodType();
        foodType.setId("ID1");

        String foodTypeName = menuEntryDto.getType().getName();
        foodType.setName(foodTypeName);
        Mockito.when(foodTypeService.findByName(foodTypeName)).thenReturn(foodType);
        menuEntryDto.getBaseIngredients().forEach(ingredientDto -> {
            Mockito.when(ingredientService.findByName(ingredientDto.getName()))
                    .thenReturn(new Ingredient(ingredientDto.getName()));
        });

        MenuEntry dao = menuEntryUtil.toDao(menuEntryDto);

        assertEquals(dao.getName(), menuEntryDto.getName());
        assertEquals(dao.getName(), menuEntryDto.getName());
        assertEquals(dao.getBasePrice(), menuEntryDto.getBasePrice());
        assertEquals(dao.getType().getName(), menuEntryDto.getType().getName());
        assertEquals(dao.getParams(), menuEntryDto.getParams());
        dao.getBaseIngredients().forEach(ingredientDao -> {
            assertTrue(menuEntryDto.getBaseIngredients().stream()
                    .anyMatch(ingredient -> ingredient.getName().equals(ingredientDao.getName())));
        });
        assertEquals(dao.getAdditionalIngredients(), menuEntryDto.getAdditionalIngredients());
    }

    @Test
    public void toDaoTest_converterThrowsException(){
        final IngredientService ingredientService = Mockito.mock(IngredientServiceImpl.class);
        final FoodTypeService foodTypeService = Mockito.mock(FoodTypeServiceImpl.class);

        final MenuEntryUtil menuEntryUtil = new MenuEntryUtilImpl(ingredientService, foodTypeService);

        FoodType foodType = new FoodType();
        foodType.setId("ID1");

        String foodTypeName = menuEntryDto.getType().getName();
        foodType.setName(foodTypeName);
        Mockito.when(foodTypeService.findByName(foodTypeName))
                .thenThrow(new NotFoundException("some food type service exception"));
        Mockito.when(foodTypeService.save(new FoodType(menuEntryDto.getType().getName())))
                .then(invocation -> invocation.getArguments()[0]);


        menuEntryDto.getBaseIngredients().forEach(ingredientDto -> {
            Mockito.when(ingredientService.findByName(ingredientDto.getName()))
                    .thenThrow(new NotFoundException("some ingredient service exception"));
            Mockito.when(ingredientService.save(new Ingredient(ingredientDto.getName())))
                    .thenAnswer(invocation -> invocation.getArguments()[0]);
        });

        MenuEntry dao = menuEntryUtil.toDao(menuEntryDto);

        assertEquals(dao.getName(), menuEntryDto.getName());
        assertEquals(dao.getName(), menuEntryDto.getName());
        assertEquals(dao.getBasePrice(), menuEntryDto.getBasePrice());
        assertEquals(dao.getType().getName(), menuEntryDto.getType().getName());
        assertEquals(dao.getParams(), menuEntryDto.getParams());
        dao.getBaseIngredients().forEach(ingredientDao -> {
            assertTrue(menuEntryDto.getBaseIngredients().stream()
                    .anyMatch(ingredient -> ingredient.getName().equals(ingredientDao.getName())));
        });
        assertEquals(dao.getAdditionalIngredients(), menuEntryDto.getAdditionalIngredients());
    }
}
