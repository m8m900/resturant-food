package restaurantCard.controller;

import day.entity.DaysOfWeeks;
import day.service.DaysOfWeeksService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import meal.entity.MealOfCard;
import restaurantCard.entity.RestaurantOfCard;
import restaurantCard.service.RestaurantCardFacade;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Named
@ViewScoped
public class RestaurantCardController implements Serializable {

    @Inject
    private RestaurantCardFacade restaurantCardFacade;
    @Inject
    private DaysOfWeeksService daysOfWeeksService;
    private List<RestaurantOfCard> restaurantCardList = new ArrayList<>();
    private boolean openDiv = false;
    private DaysOfWeeks daysOfWeeks = new DaysOfWeeks();
    private RestaurantOfCard restaurantOfCard = new RestaurantOfCard();
    private List<DaysOfWeeks> breakfast = new ArrayList<>();
    private List<DaysOfWeeks> lunch = new ArrayList<>();
    private List<DaysOfWeeks> dinner = new ArrayList<>();
    private MealOfCard mealOfCard = new MealOfCard();
    private List<DayOfWeek> daysOfWeek = Arrays.asList(DayOfWeek.values());

    @PostConstruct
    public void init() {
        fillWeekLists();
    }
    public String getLocalizedDay(DayOfWeek day) {
        Map<DayOfWeek, String> arabicDays = Map.of(
                DayOfWeek.SUNDAY, "الأحد",
                DayOfWeek.MONDAY, "الإثنين",
                DayOfWeek.TUESDAY, "الثلاثاء",
                DayOfWeek.WEDNESDAY, "الأربعاء",
                DayOfWeek.THURSDAY, "الخميس",
                DayOfWeek.FRIDAY, "الجمعة",
                DayOfWeek.SATURDAY, "السبت"
        );
        return arabicDays.get(day);
    }
    private void fillWeekLists() {
        LocalDate today = LocalDate.now();
        // حساب الفرق بين اليوم الحالي وأول يوم في الأسبوع (الأحد)
        int daysUntilSunday = DayOfWeek.SUNDAY.getValue() - today.getDayOfWeek().getValue();
        if (daysUntilSunday > 0) {
            daysUntilSunday -= 7; // لضمان أن الأيام تبدأ من الأحد للأسبوع الحالي
        }
        LocalDate startDate = today.plusDays(daysUntilSunday); // تحديد تاريخ أول يوم (الأحد)
        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            // إنشاء كائنات منفصلة لكل نوع وجبة
            DaysOfWeeks breakfastEntry = new DaysOfWeeks();
            breakfastEntry.setDate(date);
            DaysOfWeeks lunchEntry = new DaysOfWeeks();
            lunchEntry.setDate(date);
            DaysOfWeeks dinnerEntry = new DaysOfWeeks();
            dinnerEntry.setDate(date);
            breakfast.add(breakfastEntry);
            lunch.add(lunchEntry);
            dinner.add(dinnerEntry);
        }
    }
    public void onOpen() {
        if (restaurantOfCard.getName() != null && restaurantOfCard.getSite() != null) {
            openDiv = true;
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("ادخل الاسم والموقع"));
        }
    }
    public void saveInDB() {
        restaurantCardFacade.create(restaurantOfCard);
        for (DaysOfWeeks breakfast : breakfast) {
            if (breakfast.getMealOfCard() != null) {
                System.out.println("✔️ Breakfast: " + breakfast.getMealOfCard().getServing_meal());
                breakfast.setRestaurantOfCard(restaurantOfCard);
                breakfast.setOrderOfType("افطار");
                daysOfWeeksService.create(breakfast);
            } else {
                System.out.println("❌ Breakfast Meal is NULL");
            }
        }
        for (DaysOfWeeks lunch : lunch) {
            if (lunch.getMealOfCard() != null) {
                System.out.println("✔️ Lunch: " + lunch.getMealOfCard().getServing_meal());
                lunch.setRestaurantOfCard(restaurantOfCard);
                lunch.setOrderOfType("غداء");
                daysOfWeeksService.create(lunch);
            } else {
                System.out.println("❌ Lunch Meal is NULL");
            }
        }
        for (DaysOfWeeks dinner : dinner) {
            if (dinner.getMealOfCard() != null) {
                System.out.println("✔️ Dinner: " + dinner.getMealOfCard().getServing_meal());
                dinner.setRestaurantOfCard(restaurantOfCard);
                dinner.setOrderOfType("عشاء");
                daysOfWeeksService.create(dinner);
            } else {
                System.out.println("❌ Dinner Meal is NULL");
            }
        }
    }
}


