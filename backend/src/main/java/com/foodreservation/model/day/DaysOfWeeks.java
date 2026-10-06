package com.foodreservation.model.day;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.foodreservation.model.meal.MealOfCard;
import com.foodreservation.model.restaurantcard.RestaurantOfCard;
import java.io.Serializable;
import java.time.LocalDate;
import com.foodreservation.model.common.MealType;
import jakarta.validation.constraints.NotNull;


@Entity
@Getter
@Setter
public class DaysOfWeeks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "التاريخ مطلوب")
    private LocalDate date;

    @NotNull(message = "لازم تحدد الوجبة")
    @ManyToOne
    @JoinColumn(name = "meal_of_card_id")
    private MealOfCard mealOfCard;

    @NotNull(message = "لازم تحدد المطعم")
    @ManyToOne
    @JoinColumn(name = "restaurant_of_card_id")
    private RestaurantOfCard restaurantOfCard;

    @NotNull(message = "نوع الوجبة مطلوب")
    @Enumerated(EnumType.STRING)
    private MealType mealType;

}
