package com.foodreservation.mapper.day;

import com.foodreservation.model.meal.MealOfCard;
import com.foodreservation.model.restaurantcard.RestaurantOfCard;
import com.foodreservation.dto.day.DayDto;
import com.foodreservation.model.day.DaysOfWeeks;
public class DayMapper {

   public static DayDto toDto(DaysOfWeeks entity) {
        DayDto dto = new DayDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setMealType(entity.getMealType());
        dto.setMealOfCardId(entity.getMealOfCard().getId());
        dto.setRestaurantOfCardId(entity.getRestaurantOfCard().getId());
        return dto;
    }

    public static DaysOfWeeks toEntity(DayDto dto, MealOfCard mealOfCard, RestaurantOfCard restaurantOfCard) {
        DaysOfWeeks entity = new DaysOfWeeks();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setMealType(dto.getMealType());
        entity.setMealOfCard(mealOfCard);
        entity.setRestaurantOfCard(restaurantOfCard);
        return entity;
    }
}
