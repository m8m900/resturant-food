import { Component } from '@angular/core';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from 'primeng/api';
import { CutMealDay } from '../../models/cut-meal';

@Component({
  imports: [TableModule, ButtonModule, ConfirmDialogModule],
  providers: [ConfirmationService],
  selector: 'app-cut-meal',
  styleUrl: './cut-meal.css',
  templateUrl: './cut-meal.html',
})
export class CutMeal {
  constructor(
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
  ) {}

  // TODO: استبدال هذي القائمة باستدعاء REST API الفعلي (لاحقاً)
  days: CutMealDay[] = [
    {
      name: 'الأحد',
      meals: [
        { id: 1, type: 'الفطور', cut: false },
        { id: 2, type: 'الغداء', cut: true },
      ],
    },
    {
      name: 'الإثنين',
      meals: [
        { id: 3, type: 'الفطور', cut: false },
      ],
    },
  ];

  confirmCut(mealId: number): void {
    this.confirmationService.confirm({
      header: 'تأكيد',
      message: 'هل أنت متأكد من قطع هذه الوجبة؟',
      icon: 'pi pi-exclamation-triangle',
      accept: () => this.cutMeal(mealId),
    });
  }

  private cutMeal(mealId: number): void {
    // TODO: ربط عملية القطع بـ REST API (لاحقاً)
    for (const day of this.days) {
      const meal = day.meals.find((m) => m.id === mealId);
      if (meal) {
        meal.cut = true;
      }
    }
    this.messageService.add({ severity: 'success', summary: 'تم', detail: 'تم قطع الوجبة' });
  }
}
