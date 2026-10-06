package com.foodreservation.dto.day;

import java.time.LocalDate;
import com.foodreservation.model.common.MealType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class DayDto {
    private Long id;

    @NotNull(message = "التاريخ مطلوب")
    private LocalDate date;

    @NotNull(message = "نوع الوجبة مطلوب")
    private MealType mealType;

    @NotNull(message = "لازم تحدد الوجبة")
    private Long mealOfCardId;

    @NotNull(message = "لازم تحدد المطعم")
    private Long restaurantOfCardId;


}
