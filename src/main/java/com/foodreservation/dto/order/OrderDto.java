package com.foodreservation.dto.order;

import com.foodreservation.model.common.MealType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {

    private Long id;

    @NotNull(message = "وقت بداية الحجز مطلوب")
    private LocalDateTime reservationTime;

    @NotNull(message = "وقت نهاية الحجز مطلوب")
    private LocalDateTime endTime;

    private boolean cut;
    private LocalDateTime cutTime;

    @NotNull(message = "لازم تربط الحجز بيوم محدد")
    private Long daysOfWeeksId;

    //هنا فقط لعرض لسته من قائمة الوجبات 

    private MealType mealType;
}
