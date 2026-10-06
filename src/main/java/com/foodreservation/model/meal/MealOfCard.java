package com.foodreservation.model.meal;

import com.foodreservation.model.day.DaysOfWeeks;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.foodreservation.model.restaurantcard.RestaurantOfCard;
import com.foodreservation.model.common.MealType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Setter
@Getter
@Entity
@NamedQuery(name = "Meal_card.allMeal_cardCount",
        query = "SELECT COUNT(*) FROM  MealOfCard "
)
public class MealOfCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @OneToMany(mappedBy = "mealOfCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UploadedFileEntity> uploadedFileEntities = new ArrayList<>();
    ///////

    @OneToMany(mappedBy = "mealOfCard", cascade = CascadeType.ALL)
    private List<DaysOfWeeks> daysOfWeeks = new ArrayList<>();
}
