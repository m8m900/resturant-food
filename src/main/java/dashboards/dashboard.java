package dashboards;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import order.service.ReservationFacade;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import java.io.Serializable;

@Named
@ViewScoped
@Setter
@Getter
public class dashboard implements Serializable {

    @Inject
    private ReservationFacade reservationService;

    private long breakfastCount;
    private long lunchCount;
    private long dinnerCount;
    private long totalRegisteredPeople;

    private PieChartModel pieModel;
    private BarChartModel barModel;

    @PostConstruct
    public void init() {

        createPieModel();
        createBarModel();
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        pieModel.set("فطور", breakfastCount);
        pieModel.set("غداء", lunchCount);
        pieModel.set("عشاء", dinnerCount);
        pieModel.setTitle("إحصائيات الحجوزات");
        pieModel.setLegendPosition("w");
        pieModel.setShowDataLabels(true);
    }

    private void createBarModel() {
        barModel = new BarChartModel();
        ChartSeries meals = new ChartSeries();
        meals.setLabel("عدد الأشخاص");

        meals.set("فطور", breakfastCount);
        meals.set("غداء", lunchCount);
        meals.set("عشاء", dinnerCount);
        meals.set("إجمالي المسجلين", totalRegisteredPeople);

        barModel.addSeries(meals);
        barModel.setTitle("إجمالي الحجوزات");
        barModel.setLegendPosition("ne");
    }
}
