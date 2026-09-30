import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PanelMenuModule } from 'primeng/panelmenu';
import { MenuItem } from 'primeng/api';

@Component({
  imports: [RouterOutlet, PanelMenuModule],
  selector: 'app-layout',
  styleUrl: './layout.css',
  templateUrl: './layout.html',
})
export class Layout {
  // نفس روابط menu.xhtml الأصلية
  menuItems: MenuItem[] = [
    { label: 'الصفحة الرئيسية', icon: 'pi pi-home', routerLink: '/dashboard' },
    {
      label: 'الحجوزات',
      items: [{ label: 'اضافة الحجوزات', icon: 'pi pi-plus', routerLink: '/reservations' }],
    },
    {
      label: 'بطاقة المطبخ',
      items: [
        { label: 'اضافة وجبة', icon: 'pi pi-plus', routerLink: '/meals/new' },
        { label: 'جدول اضافة الوجبة', icon: 'pi pi-list', routerLink: '/meals' },
      ],
    },
    {
      label: 'بطاقة المطعم',
      items: [
        { label: 'اضافة وجبات لأيام الاسبوع', icon: 'pi pi-plus', routerLink: '/restaurants/new' },
        { label: 'جدول ايام الوجبات', icon: 'pi pi-list', routerLink: '/restaurants' },
      ],
    },
    { label: 'اضافة id', icon: 'pi pi-plus', routerLink: '/register-user' },
    { label: 'الاعدادات', icon: 'pi pi-cog', routerLink: '/reservation-settings' },
    { label: 'القطع', icon: 'pi pi-cog', routerLink: '/cutting-meal' },
  ];
}
