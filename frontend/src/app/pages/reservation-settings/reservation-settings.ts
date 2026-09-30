import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DatePickerModule } from 'primeng/datepicker';
import { ButtonModule } from 'primeng/button';
import { PanelModule } from 'primeng/panel';
import { MessageService } from 'primeng/api';
import { ReservationSettings as ReservationSettingsModel } from '../../models/reservation-settings';

@Component({
  imports: [FormsModule, DatePickerModule, ButtonModule, PanelModule],
  selector: 'app-reservation-settings',
  styleUrl: './reservation-settings.css',
  templateUrl: './reservation-settings.html',
})
export class ReservationSettings {
  settings: ReservationSettingsModel = {
    breakfastStart: null,
    breakfastEnd: null,
    lunchStart: null,
    lunchEnd: null,
    dinnerStart: null,
    dinnerEnd: null,
  };

  constructor(private messageService: MessageService) {}

  saveSettings(): void {
    // TODO: ربط الحفظ بـ REST API (لاحقاً)
    this.messageService.add({ severity: 'success', summary: 'تم', detail: 'تم حفظ الإعدادات' });
  }
}
