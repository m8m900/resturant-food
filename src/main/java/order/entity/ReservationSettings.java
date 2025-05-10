package order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class ReservationSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime breakfastStart;
    private LocalTime breakfastEnd;

    private LocalTime lunchStart;
    private LocalTime lunchEnd;

    private LocalTime dinnerStart;
    private LocalTime dinnerEnd;
}
