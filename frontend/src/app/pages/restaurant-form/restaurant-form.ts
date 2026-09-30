import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { SelectModule } from 'primeng/select';
import { MessageService } from 'primeng/api';
import { DaysOfWeeks } from '../../models/days-of-weeks';
import { MealOfCard } from '../../models/meal-of-card';

const ARABIC_DAYS = ['الأحد', 'الإثنين', 'الثلاثاء', 'الأربعاء', 'الخميس', 'الجمعة', 'السبت'];

function buildEmptyWeek(): DaysOfWeeks[] {
  return ARABIC_DAYS.map((dayLabel) => ({
    date: '',
    dayLabel,
    mealOfCard: null,
  }));
}

@Component({
  imports: [FormsModule, InputTextModule, ButtonModule, SelectModule],
  selector: 'app-restaurant-form',
  styleUrl: './restaurant-form.css',
  templateUrl: './restaurant-form.html',
})
export class RestaurantForm {
  name = '';
  site = '';
  openDiv = false;

  breakfast: DaysOfWeeks[] = buildEmptyWeek();
  lunch: DaysOfWeeks[] = buildEmptyWeek();
  dinner: DaysOfWeeks[] = buildEmptyWeek();

  // TODO: استبدال هذي القوائم باستدعاء REST API الفعلي مفلترة حسب نوع الوجبة (لاحقاً)
  breakfastMeals: MealOfCard[] = [
    { id: 1, ingredients: 'بيض ومناقيش', price: '1000', servingMeal: 'الفطور' },
  ];
  lunchMeals: MealOfCard[] = [
    { id: 2, ingredients: 'رز ودجاج', price: '2500', servingMeal: 'الغداء' },
  ];
  dinnerMeals: MealOfCard[] = [
    { id: 3, ingredients: 'شاورما', price: '2000', servingMeal: 'العشاء' },
  ];

  constructor(private messageService: MessageService) {}

  onOpen(): void {
    if (this.name.trim() && this.site.trim()) {
      this.openDiv = true;
    } else {
      // نفس رسالة JSF الأصلية: FacesContext.addMessage(null, new FacesMessage("ادخل الاسم والموقع"))
      this.messageService.add({ severity: 'warn', summary: 'تنبيه', detail: 'ادخل الاسم والموقع' });
    }
  }

  saveInDb(): void {
    // TODO: ربط هذا الفعل بـ REST API لحفظ المطعم وجدول الوجبات (لاحقاً)
  }
}
