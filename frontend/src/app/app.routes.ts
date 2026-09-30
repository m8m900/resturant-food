import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { EnterUserId } from './pages/enter-user-id/enter-user-id';
import { Layout } from './layout/layout';
import { RestaurantList } from './pages/restaurant-list/restaurant-list';
import { RestaurantForm } from './pages/restaurant-form/restaurant-form';
import { MealList } from './pages/meal-list/meal-list';
import { MealForm } from './pages/meal-form/meal-form';
import { CutMeal } from './pages/cut-meal/cut-meal';
import { ReservationList } from './pages/reservation-list/reservation-list';
import { ReservationSettings } from './pages/reservation-settings/reservation-settings';
import { Dashboard } from './pages/dashboard/dashboard';
import { RegisterUser } from './pages/register-user/register-user';

export const routes: Routes = [
  // صفحات مستقلة بدون القالب (نفس الأصل: login و addUserId ما كانت جوه template.xhtml)
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'enter-user-id', component: EnterUserId },

  // باقي الصفحات جوه القالب المشترك (topbar + sidebar) - نفس WEB-INF/template.xhtml الأصلي
  {
    path: '',
    component: Layout,
    children: [
      { path: 'restaurants', component: RestaurantList },
      { path: 'restaurants/new', component: RestaurantForm },
      { path: 'meals', component: MealList },
      { path: 'meals/new', component: MealForm },
      { path: 'meals/:id/edit', component: MealForm },
      { path: 'cutting-meal', component: CutMeal },
      { path: 'reservations', component: ReservationList },
      { path: 'reservation-settings', component: ReservationSettings },
      { path: 'dashboard', component: Dashboard },
      { path: 'register-user', component: RegisterUser },
    ],
  },
];
