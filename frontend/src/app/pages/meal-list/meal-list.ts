import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { MealOfCard } from '../../models/meal-of-card';

@Component({
  imports: [TableModule, ButtonModule, DialogModule, RouterLink],
  selector: 'app-meal-list',
  styleUrl: './meal-list.css',
  templateUrl: './meal-list.html',
})
export class MealList {
  // TODO: استبدال هذي القائمة باستدعاء REST API الفعلي (لاحقاً)
  meals: MealOfCard[] = [
    { id: 1, ingredients: 'بيض ومناقيش', price: '1000', servingMeal: 'الفطور', details: 'وجبة خفيفة' },
    { id: 2, ingredients: 'رز ودجاج', price: '2500', servingMeal: 'الغداء', details: 'وجبة رئيسية' },
  ];

  imageDialogVisible: Record<number, boolean> = {};

  showImage(mealId: number): void {
    this.imageDialogVisible[mealId] = true;
  }

  deleteMeal(meal: MealOfCard): void {
    // TODO: ربط الحذف بـ REST API (لاحقاً)
    this.meals = this.meals.filter((m) => m.id !== meal.id);
  }
}
