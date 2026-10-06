package com.foodreservation.model.restaurantcard;

import com.foodreservation.model.day.DaysOfWeeks;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.proxy.HibernateProxy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class RestaurantOfCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "اسم المطعم مطلوب")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "موقع المطعم مطلوب")
    @Size(max = 100)
    private String site;
    @OneToMany(mappedBy = "restaurantOfCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DaysOfWeeks> daysOfWeeks = new ArrayList<>();

 }
