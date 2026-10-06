package com.foodreservation.dto.meal;

import com.foodreservation.model.common.MealType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MealDto {

    private Long id;

    @NotBlank(message = "مكونات الوجبة مطلوبة")
    @Size(max = 255)
    private String ingredients;

    @NotNull(message = "سعر الوجبة مطلوب")
    @Positive(message = "السعر لازم يكون أكبر من صفر")
    private BigDecimal price;

    @Size(max = 500)
    private String details;

    @NotNull(message = "نوع الوجبة مطلوب")
    private MealType mealType;
}
