package com.foodreservation.mapper.restaurantcard;

import com.foodreservation.dto.restaurantcard.RestaurantCardDto;
import com.foodreservation.model.restaurantcard.RestaurantOfCard;

public class RestaurantCardMapper {

    public static RestaurantCardDto toDto(RestaurantOfCard entity) {
        RestaurantCardDto dto = new RestaurantCardDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSite(entity.getSite());
        return dto;
    
    }

    public static RestaurantOfCard toEntity(RestaurantCardDto dto) {

        RestaurantOfCard entity = new RestaurantOfCard();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSite(dto.getSite());
        return  entity;
    }
}
