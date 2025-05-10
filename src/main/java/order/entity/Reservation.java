package order.entity;

import cutMeal.entity.MealCut;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderType; // نوع الوجبة (إفطار، غداء، عشاء)
    private LocalDateTime reservationTime;
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealCut> mealCuts = new ArrayList<>();

}
