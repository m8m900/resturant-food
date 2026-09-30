import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { MessageService } from 'primeng/api';

@Component({
  imports: [FormsModule, InputTextModule, PasswordModule, ButtonModule],
  selector: 'app-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
})
export class Login {
  username = '';
  password = '';

  constructor(private messageService: MessageService) {}

  login(): void {
    // TODO: ربط هذا الفعل بـ REST API الخاص بتسجيل الدخول (لاحقاً)
    // عند فشل تسجيل الدخول، النداء الأصلي بـ JSF كان يعرض:
    // addMessage(SEVERITY_ERROR, "خطأ", "كلمة المرور خطأ")
    // this.messageService.add({ severity: 'error', summary: 'خطأ', detail: 'كلمة المرور خطأ' });
  }
}
