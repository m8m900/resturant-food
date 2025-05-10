package order.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import order.entity.Reservation;
import order.entity.ReservationSettings;
import order.service.ReservationFacade;
import order.service.ReservationSettingsFacade;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Named
@ViewScoped
public class ReservationBean implements Serializable {
    private ScheduleModel eventModel;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String selectedMeal;
    private List<String> mealOptions;
    private Reservation reservation;
    private String serverTimeZone;
    private Date minDate;
    private Date firstDayOfMonth;
    private Date lastDayOfMonth;
    private LocalDate editDate; // تاريخ الحجز المعدل

    @Inject
    private ReservationFacade reservationFacade;
    @Inject
    private ReservationSettingsFacade settingsFacade;

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        mealOptions = Arrays.asList("إفطار", "غداء", "عشاء");
        serverTimeZone = java.time.ZoneId.systemDefault().getId();
        minDate = new Date();

        // أول وآخر يوم من الشهر الحالي
        LocalDate today = LocalDate.now();
        firstDayOfMonth = java.sql.Date.valueOf(today.withDayOfMonth(1));
        lastDayOfMonth = java.sql.Date.valueOf(today.withDayOfMonth(today.lengthOfMonth()));

        loadReservations();
    }

    public void loadReservations() {
        List<Reservation> reservations = reservationFacade.getAllReservations();
        eventModel.clear();
        for (Reservation res : reservations) {
            ScheduleEvent<?> event = DefaultScheduleEvent.builder()
                    .title(res.getOrderType())
                    .startDate(res.getReservationTime())
                    .endDate(res.getEndTime())
                    .styleClass(getEventStyleClass(res.getOrderType()))
                    .data(res)
                    .build();
            eventModel.addEvent(event);
        }
    }

    public void addReservation() {
        reservation = null;
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate()) + 1;

        ReservationSettings settings = settingsFacade.getSettings();
        if (settings == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "تنبيه", "لم يتم ضبط إعدادات الحجز بعد!"));
            return;
        }

        for (int i = 0; i < daysBetween; i++) {
            LocalDate currentDate = startDate.toLocalDate().plusDays(i);

            if (currentDate.getMonthValue() != LocalDate.now().getMonthValue()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "تنبيه", "لا يمكنك الحجز خارج الشهر الحالي!"));
                continue;
            }

            LocalDateTime fixedStart;
            LocalDateTime fixedEnd;

            switch (selectedMeal) {
                case "إفطار":
                    fixedStart = LocalDateTime.of(currentDate, settings.getBreakfastStart());
                    fixedEnd = LocalDateTime.of(currentDate, settings.getBreakfastEnd());
                    break;
                case "غداء":
                    fixedStart = LocalDateTime.of(currentDate, settings.getLunchStart());
                    fixedEnd = LocalDateTime.of(currentDate, settings.getLunchEnd());
                    break;
                case "عشاء":
                    fixedStart = LocalDateTime.of(currentDate, settings.getDinnerStart());
                    fixedEnd = LocalDateTime.of(currentDate, settings.getDinnerEnd());
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "تنبيه", "يرجى اختيار نوع وجبة صالح!"));
                    continue;
            }

            if (currentDate.equals(LocalDate.now()) && LocalDateTime.now().isAfter(fixedStart)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "تنبيه", "لا يمكنك حجز " + selectedMeal + " لهذا اليوم لأن الوقت المحدد قد مر"));
                continue;
            }

            Reservation res = new Reservation();
            res.setReservationTime(fixedStart);
            res.setEndTime(fixedEnd);
            res.setOrderType(selectedMeal);
            reservationFacade.create(res);
        }

        loadReservations();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("تمت إضافة الحجز بنجاح"));

        selectedMeal = null;
        startDate = null;
        endDate = null;
    }

    public void onDateSelect(SelectEvent<LocalDateTime> event) {
        LocalDate selectedDate = event.getObject().toLocalDate();
        LocalDate today = LocalDate.now();

        if (selectedDate.isBefore(today) || selectedDate.getMonthValue() != today.getMonthValue()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "تحذير", "لا يمكنك الحجز خارج الشهر الحالي!"));
            return;
        }

        this.startDate = selectedDate.atStartOfDay();
        this.endDate = selectedDate.atStartOfDay();
        PrimeFaces.current().executeScript("PF('eventDialog').show();");
    }

    public void onEventSelect(SelectEvent<ScheduleEvent<?>> event) {
        ScheduleEvent<?> scheduleEvent = event.getObject();
        if (scheduleEvent.getData() instanceof Reservation) {
            this.reservation = (Reservation) scheduleEvent.getData();
            this.selectedMeal = reservation.getOrderType();
            this.editDate = reservation.getReservationTime().toLocalDate(); // حفظ التاريخ المعدل
            PrimeFaces.current().executeScript("PF('editDialog').show();");
        }
    }

    public void updateReservation() {
        if (reservation != null && editDate != null) {
            reservation.setOrderType(selectedMeal);

            ReservationSettings settings = settingsFacade.getSettings();
            LocalDateTime fixedStart, fixedEnd;

            // تعديل وقت الحجز بناءً على الوجبة والتاريخ المعدل
            switch (selectedMeal) {
                case "إفطار":
                    fixedStart = LocalDateTime.of(editDate, settings.getBreakfastStart());
                    fixedEnd = LocalDateTime.of(editDate, settings.getBreakfastEnd());
                    break;
                case "غداء":
                    fixedStart = LocalDateTime.of(editDate, settings.getLunchStart());
                    fixedEnd = LocalDateTime.of(editDate, settings.getLunchEnd());
                    break;
                case "عشاء":
                    fixedStart = LocalDateTime.of(editDate, settings.getDinnerStart());
                    fixedEnd = LocalDateTime.of(editDate, settings.getDinnerEnd());
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطأ", "نوع وجبة غير معروف"));
                    return;
            }

            reservation.setReservationTime(fixedStart);
            reservation.setEndTime(fixedEnd);

            reservationFacade.create(reservation);
            reservation = null;
            selectedMeal = null;
            editDate = null;

            loadReservations();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("تم تعديل الحجز بنجاح"));
        }
    }

    public void deleteReservation() {
        if (reservation != null) {
            reservationFacade.remove(reservation);
            reservation = null;
            selectedMeal = null;
            loadReservations();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("تم حذف الحجز"));
        }
    }

    private String getEventStyleClass(String orderType) {
        switch (orderType) {
            case "إفطار": return "breakfast-event";
            case "غداء": return "lunch-event";
            case "عشاء": return "dinner-event";
            default: return "";
        }
    }
}
