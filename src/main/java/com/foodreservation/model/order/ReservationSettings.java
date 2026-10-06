package com.foodreservation.model.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "وقت بداية الإفطار مطلوب")
    private LocalTime breakfastStart;

    @NotNull(message = "وقت نهاية الإفطار مطلوب")
    private LocalTime breakfastEnd;

    @NotNull(message = "وقت بداية الغداء مطلوب")
    private LocalTime lunchStart;

    @NotNull(message = "وقت نهاية الغداء مطلوب")
    private LocalTime lunchEnd;

    @NotNull(message = "وقت بداية العشاء مطلوب")
    private LocalTime dinnerStart;

    @NotNull(message = "وقت نهاية العشاء مطلوب")
    private LocalTime dinnerEnd;
}
