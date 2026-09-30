import { Component } from '@angular/core';
import { ChartModule } from 'primeng/chart';
import { PanelModule } from 'primeng/panel';

@Component({
  imports: [ChartModule, PanelModule],
  selector: 'app-dashboard',
  styleUrl: './dashboard.css',
  templateUrl: './dashboard.html',
})
export class Dashboard {
  // TODO: استبدال هذي الأرقام باستدعاء REST API الفعلي (لاحقاً)
  breakfastCount = 12;
  lunchCount = 20;
  dinnerCount = 8;

  breakfastChart = this.buildPieData(this.breakfastCount, '#42A5F5');
  lunchChart = this.buildPieData(this.lunchCount, '#EF5350');
  dinnerChart = this.buildPieData(this.dinnerCount, '#66BB6A');

  private buildPieData(count: number, color: string) {
    return {
      labels: ['محجوز'],
      datasets: [
        {
          data: [count],
          backgroundColor: [color],
        },
      ],
    };
  }
}
