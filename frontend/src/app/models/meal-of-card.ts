export interface MealOfCard {
  id?: number;
  ingredients: string;
  price: string;
  servingMeal: string; // الفطور / الغداء / العشاء
  details?: string;
}
