package day.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import meal.entity.MealOfCard;
import restaurantCard.entity.RestaurantOfCard;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class DaysOfWeeks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderOfType;
    @Transient
    private LocalDate date;

    @Transient
    private MealOfCard tempMealOfCard;

    @ManyToOne
    @JoinColumn(name = "meal_of_card_id")
    private MealOfCard mealOfCard;

    @ManyToOne
    @JoinColumn(name = "restaurant_of_card_id")
    private RestaurantOfCard restaurantOfCard;

    public void setMealOfCard(MealOfCard mealOfCard) {
        if(mealOfCard != null){
            this.tempMealOfCard = mealOfCard;
            this.mealOfCard = tempMealOfCard;
        }

    }
}
