package com.foodreservation.mapper.meal;

import com.foodreservation.dto.meal.MealDto;
import com.foodreservation.model.meal.MealOfCard;

public class MealMapper {

    public static MealDto toDto(MealOfCard entity) {
        MealDto dto = new MealDto();
        dto.setId(entity.getId());
        dto.setIngredients(entity.getIngredients());
        dto.setPrice(entity.getPrice());
        dto.setDetails(entity.getDetails());
        dto.setMealType(entity.getMealType());
        return dto;
        
    }

    public static MealOfCard toEntity(MealDto dto) {
       MealOfCard entity = new MealOfCard();
        entity.setId(dto.getId());
        entity.setIngredients(dto.getIngredients());
        entity.setPrice(dto.getPrice());
        entity.setDetails(dto.getDetails());
        entity.setMealType(dto.getMealType());
        return entity;
    }
}
