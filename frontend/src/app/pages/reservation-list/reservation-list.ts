import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FullCalendarModule } from '@fullcalendar/angular';
import { CalendarOptions, EventClickArg } from '@fullcalendar/core';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { SelectModule } from 'primeng/select';
import { DatePickerModule } from 'primeng/datepicker';
import { MessageService } from 'primeng/api';
import { Reservation } from '../../models/reservation';

const EVENT_STYLE: Record<string, string> = {
  إفطار: 'breakfast-event',
  غداء: 'lunch-event',
  عشاء: 'dinner-event',
};

@Component({
  imports: [
    FormsModule,
    FullCalendarModule,
    ButtonModule,
    DialogModule,
    SelectModule,
    DatePickerModule,
  ],
  selector: 'app-reservation-list',
  styleUrl: './reservation-list.css',
  templateUrl: './reservation-list.html',
})
export class ReservationList {
  mealOptions = ['إفطار', 'غداء', 'عشاء'];

  // TODO: استبدال هذي القائمة باستدعاء REST API الفعلي (لاحقاً)
  reservations: Reservation[] = [
    { id: 1, orderType: 'إفطار', reservationTime: '2026-10-01T08:00', endTime: '2026-10-01T09:00' },
    { id: 2, orderType: 'غداء', reservationTime: '2026-10-01T13:00', endTime: '2026-10-01T14:00' },
  ];

  calendarOptions: CalendarOptions = {
    plugins: [timeGridPlugin, interactionPlugin],
    initialView: 'timeGridWeek',
    allDaySlot: false,
    headerToolbar: {
      start: 'title',
      center: '',
      end: 'timeGridWeek,timeGridDay today prev,next',
    },
    events: this.toEvents(this.reservations),
    eventClick: (arg) => this.onEventClick(arg),
  };

  // نافذة الإضافة
  addDialogVisible = false;
  addSelectedMeal = '';
  startDate: Date | null = null;
  endDate: Date | null = null;

  // نافذة التعديل/الحذف
  editDialogVisible = false;
  selectedReservation: Reservation | null = null;
  editSelectedMeal = '';
  editDate: Date | null = null;

  constructor(private messageService: MessageService) {}

  private toEvents(reservations: Reservation[]) {
    return reservations.map((r) => ({
      id: String(r.id),
      title: r.orderType,
      start: r.reservationTime,
      end: r.endTime,
      classNames: [EVENT_STYLE[r.orderType] ?? ''],
      extendedProps: { reservation: r },
    }));
  }

  private refreshEvents(): void {
    this.calendarOptions = { ...this.calendarOptions, events: this.toEvents(this.reservations) };
  }

  openAddDialog(): void {
    this.addDialogVisible = true;
  }

  addReservation(): void {
    // نفس التحقق الأصلي بـ JSF: "يرجى ملء كل الحقول المطلوبة"
    if (!this.addSelectedMeal || !this.startDate || !this.endDate) {
      this.messageService.add({
        severity: 'warn',
        summary: 'تحذير',
        detail: 'يرجى ملء كل الحقول المطلوبة',
      });
      return;
    }
    // TODO: ربط الإضافة بـ REST API (لاحقاً) - يشمل توليد حجز لكل يوم بين startDate و endDate
    this.addDialogVisible = false;
    this.addSelectedMeal = '';
    this.startDate = null;
    this.endDate = null;
    this.messageService.add({ severity: 'success', summary: 'نجاح', detail: 'تمت إضافة الحجز بنجاح' });
  }

  onEventClick(arg: EventClickArg): void {
    const reservation = arg.event.extendedProps['reservation'] as Reservation;
    this.selectedReservation = reservation;
    this.editSelectedMeal = reservation.orderType;
    this.editDate = new Date(reservation.reservationTime);
    this.editDialogVisible = true;
  }

  updateReservation(): void {
    // TODO: ربط التعديل بـ REST API (لاحقاً)
    this.editDialogVisible = false;
    this.refreshEvents();
    this.messageService.add({ severity: 'success', summary: 'تم', detail: 'تم تعديل الحجز بنجاح' });
  }

  deleteReservation(): void {
    // TODO: ربط الحذف بـ REST API (لاحقاً)
    if (this.selectedReservation) {
      this.reservations = this.reservations.filter((r) => r.id !== this.selectedReservation!.id);
    }
    this.editDialogVisible = false;
    this.refreshEvents();
    this.messageService.add({ severity: 'success', summary: 'تم', detail: 'تم حذف الحجز' });
  }
}
