package restaurantCard.entity;

import day.entity.DaysOfWeeks;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import meal.entity.MealOfCard;
import org.hibernate.proxy.HibernateProxy;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class RestaurantOfCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String site;



    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @OneToMany(mappedBy = "restaurantOfCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DaysOfWeeks> daysOfWeeks = new ArrayList<>();

    @Override
    public String toString() {
        return "RestaurantOfCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';

    }}
