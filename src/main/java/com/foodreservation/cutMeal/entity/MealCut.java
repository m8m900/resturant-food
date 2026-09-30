package cutMeal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import order.entity.Reservation;
import peopleAccount.entity.UserEntry;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MealCut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_entry_id")
    private UserEntry userEntry;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private LocalDateTime cutTime; // وقت قطع الوجبة
    private boolean cut;
}