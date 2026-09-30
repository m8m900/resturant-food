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

    // لإضافة الحجز
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String addSelectedMeal;

    // لتعديل الحجز
    private Reservation reservation;
    private LocalDate editDate;
    private String editSelectedMeal;

    // إعدادات عامة
    private List<String> mealOptions;
    private String serverTimeZone;
    private Date minDate;

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
        loadReservations();
    }

    public void loadReservations() {
        eventModel.clear();
        List<Reservation> reservations = reservationFacade.getAllReservations();
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
        if (startDate == null || endDate == null || addSelectedMeal == null) {
            showMessage(FacesMessage.SEVERITY_WARN, "تحذير", "يرجى ملء كل الحقول المطلوبة");
            return;
        }

        ReservationSettings settings = settingsFacade.getSettings();
        if (settings == null) {
            showMessage(FacesMessage.SEVERITY_WARN, "تنبيه", "لم يتم ضبط إعدادات الحجز بعد!");
            return;
        }

        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate()) + 1;

        for (int i = 0; i < daysBetween; i++) {
            LocalDate currentDate = startDate.toLocalDate().plusDays(i);

            if (currentDate.getMonthValue() != LocalDate.now().getMonthValue()) {
                showMessage(FacesMessage.SEVERITY_WARN, "تنبيه", "لا يمكنك الحجز خارج الشهر الحالي!");
                continue;
            }

            LocalDateTime fixedStart;
            LocalDateTime fixedEnd;

            switch (addSelectedMeal) {
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
                    showMessage(FacesMessage.SEVERITY_WARN, "تنبيه", "يرجى اختيار نوع وجبة صالح!");
                    continue;
            }

            if (currentDate.equals(LocalDate.now()) && LocalDateTime.now().isAfter(fixedStart)) {
                showMessage(FacesMessage.SEVERITY_WARN, "تنبيه", "لا يمكنك حجز " + addSelectedMeal + " لهذا اليوم لأن الوقت المحدد قد مر");
                continue;
            }

            Reservation res = new Reservation();
            res.setOrderType(addSelectedMeal);
            res.setReservationTime(fixedStart);
            res.setEndTime(fixedEnd);

            try {
                reservationFacade.create(res);
            } catch (Exception e) {
                showMessage(FacesMessage.SEVERITY_ERROR, "خطأ", "فشل إضافة الحجز: " + e.getMessage());
                return;
            }
        }

        loadReservations();
        resetFields();
        showMessage(FacesMessage.SEVERITY_INFO, "نجاح", "تمت إضافة الحجز بنجاح");
    }

    public void updateReservation() {
        if (reservation == null || editDate == null || editSelectedMeal == null) return;

        ReservationSettings settings = settingsFacade.getSettings();
        LocalDateTime fixedStart;
        LocalDateTime fixedEnd;

        switch (editSelectedMeal) {
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
                showMessage(FacesMessage.SEVERITY_ERROR, "خطأ", "نوع وجبة غير معروف");
                return;
        }

        reservation.setOrderType(editSelectedMeal);
        reservation.setReservationTime(fixedStart);
        reservation.setEndTime(fixedEnd);

        reservationFacade.create(reservation);

        loadReservations();
        resetFields();
        showMessage(FacesMessage.SEVERITY_INFO, "تم", "تم تعديل الحجز بنجاح");
    }

    public void deleteReservation() {
        if (reservation != null) {
            reservationFacade.remove(reservation);
            loadReservations();
            resetFields();
            showMessage(FacesMessage.SEVERITY_INFO, "تم", "تم حذف الحجز");
        }
    }

    public void onEventSelect(SelectEvent<ScheduleEvent<?>> event) {
        ScheduleEvent<?> scheduleEvent = event.getObject();

        if (scheduleEvent.getData() instanceof Reservation) {
            this.reservation = (Reservation) scheduleEvent.getData();
            this.editSelectedMeal = reservation.getOrderType();
            this.editDate = reservation.getReservationTime().toLocalDate();
        }
    }

    private void showMessage(FacesMessage.Severity severity, String title, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, detail));
    }

    private void resetFields() {
        this.reservation = null;
        this.addSelectedMeal = null;
        this.editSelectedMeal = null;
        this.startDate = null;
        this.endDate = null;
        this.editDate = null;
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
