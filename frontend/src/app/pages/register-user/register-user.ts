import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { MessageModule } from 'primeng/message';
import { MessageService } from 'primeng/api';

@Component({
  imports: [FormsModule, InputTextModule, ButtonModule, MessageModule],
  selector: 'app-register-user',
  styleUrl: './register-user.css',
  templateUrl: './register-user.html',
})
export class RegisterUser {
  userId = '';
  errorMessage = '';

  constructor(private messageService: MessageService) {}

  save(): void {
    if (!this.userId.trim()) {
      this.errorMessage = 'يرجى إدخال اسم المستخدم';
      return;
    }
    // TODO: ربط الحفظ بـ REST API (لاحقاً)
    this.userId = '';
    this.errorMessage = '';
    this.messageService.add({ severity: 'success', summary: 'تم', detail: 'تم الحفظ' });
  }
}
