import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { MessageModule } from 'primeng/message';

@Component({
  imports: [FormsModule, InputTextModule, ButtonModule, MessageModule],
  selector: 'app-enter-user-id',
  styleUrl: './enter-user-id.css',
  templateUrl: './enter-user-id.html',
})
export class EnterUserId {
  userId = '';
  errorMessage = '';

  goToOrderPage(): void {
    if (!this.userId.trim()) {
      this.errorMessage = 'يرجى إدخال اسم المستخدم';
      return;
    }
    // TODO: ربط هذا الفعل بـ REST API للتحقق من رقم المستخدم (لاحقاً)
    // عند عدم وجود المستخدم، النداء الأصلي بـ JSF كان يعرض: addMessage(SEVERITY_ERROR, "خطأ", "userId خطاء")
  }
}
