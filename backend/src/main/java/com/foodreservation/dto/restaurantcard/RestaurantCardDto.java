package com.foodreservation.dto.restaurantcard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RestaurantCardDto {

     private Long id;

    @NotBlank(message = "اسم المطعم مطلوب")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "موقع المطعم مطلوب")
    @Size(max = 100)
    private String site;
}


