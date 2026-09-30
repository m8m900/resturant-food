import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { TextareaModule } from 'primeng/textarea';
import { SelectModule } from 'primeng/select';
import { ButtonModule } from 'primeng/button';
import { FileUploadModule } from 'primeng/fileupload';
import { MessageService } from 'primeng/api';
import { MealOfCard } from '../../models/meal-of-card';

@Component({
  imports: [
    FormsModule,
    InputTextModule,
    TextareaModule,
    SelectModule,
    ButtonModule,
    FileUploadModule,
  ],
  selector: 'app-meal-form',
  styleUrl: './meal-form.css',
  templateUrl: './meal-form.html',
})
export class MealForm {
  meal: MealOfCard = {
    ingredients: '',
    price: '',
    servingMeal: '',
    details: '',
  };

  mealTypes = ['الفطور', 'الغداء', 'العشاء'];

  constructor(private messageService: MessageService) {}

  onUpload(): void {
    // TODO: ربط رفع الصورة بـ REST API (لاحقاً)
    this.messageService.add({ severity: 'success', summary: 'تم', detail: 'تم رفع الصورة' });
  }

  saveInDb(): void {
    // TODO: ربط الحفظ بـ REST API (لاحقاً)
    this.messageService.add({ severity: 'success', summary: 'تم الحفظ', detail: 'تم حفظ الوجبة بنجاح' });
  }
}
