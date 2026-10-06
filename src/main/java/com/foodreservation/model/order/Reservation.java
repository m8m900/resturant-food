package com.foodreservation.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.foodreservation.model.day.DaysOfWeeks;
import jakarta.validation.constraints.NotNull;



@Entity
@Getter
@Setter
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "وقت بداية الحجز مطلوب")
    private LocalDateTime reservationTime;

    @NotNull(message = "وقت نهاية الحجز مطلوب")
    private LocalDateTime endTime;

    private boolean cut;
    private LocalDateTime cutTime; // وقت الاستلام

    // نوع الوجبة يُقرأ من daysOfWeeks.getMealType() - ما نكرره هنا
    @NotNull(message = "لازم تربط الحجز بيوم/وجبة/مطعم محدد")
    @ManyToOne
    @JoinColumn(name = "days_of_weeks_id")
    private DaysOfWeeks daysOfWeeks;

}
