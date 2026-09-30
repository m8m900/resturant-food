package order.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import order.entity.ReservationSettings;
import order.service.ReservationSettingsFacade;
import java.io.Serializable;
import java.time.LocalTime;

@Named
@ViewScoped
@Getter
@Setter
public class ReservationSettingsBean implements Serializable {
    private ReservationSettings settings;

    @Inject
    private ReservationSettingsFacade settingsFacade;

    @PostConstruct
    public void init() {
        settings = settingsFacade.getSettings();
        if (settings == null) {
            settings = new ReservationSettings();
            settings.setBreakfastStart(LocalTime.of(5, 0));
            settings.setBreakfastEnd(LocalTime.of(6, 0));
            settings.setLunchStart(LocalTime.of(12, 0));
            settings.setLunchEnd(LocalTime.of(15, 0));
            settings.setDinnerStart(LocalTime.of(18, 0));
            settings.setDinnerEnd(LocalTime.of(20, 0));
        }
    }

    public void saveSettings() {
        settingsFacade.saveSettings(settings);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("تم حفظ الإعدادات بنجاح"));
    }
}
