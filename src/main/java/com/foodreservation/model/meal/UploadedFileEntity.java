package com.foodreservation.model.meal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
public class UploadedFileEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "مسار الملف مطلوب")
    private String filePath;

    @NotNull(message = "لازم تربط الصورة بوجبة")
    @ManyToOne
    @JoinColumn(name = "meal_card_id")
    private MealOfCard mealOfCard;
}