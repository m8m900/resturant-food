import { MealOfCard } from './meal-of-card';

export interface DaysOfWeeks {
  id?: number;
  date: string; // ISO date
  dayLabel: string; // اسم اليوم بالعربي
  orderOfType?: string; // افطار / غداء / عشاء
  mealOfCard: MealOfCard | null;
}
