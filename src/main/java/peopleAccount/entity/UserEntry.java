package peopleAccount.entity;

import cutMeal.entity.MealCut;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class UserEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @OneToMany(mappedBy = "userEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealCut> mealCuts = new ArrayList<>();

}
