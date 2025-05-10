package meal.entity;

import day.entity.DaysOfWeeks;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import restaurantCard.entity.RestaurantOfCard;

import java.io.Serializable;
import java.util.*;

@Setter
@Getter
@Entity
@NamedQuery(name = "Meal_card.allMeal_cardCount",
        query = "SELECT COUNT(*) FROM  MealOfCard "
)
public class MealOfCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ingredients;
    private String price;
    private String Serving_meal;
    private String details;

    @OneToMany(mappedBy = "meal_card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UploadedFileEntity> uploadedFileEntities = new ArrayList<>();
    ///////

    @OneToMany(mappedBy = "mealOfCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DaysOfWeeks> daysOfWeeks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealOfCard that = (MealOfCard) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "MealOfCard{" +
                "id=" + id +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }


}
