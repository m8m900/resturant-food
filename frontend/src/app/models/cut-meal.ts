export interface CutMealItem {
  id: number;
  type: string; // نوع الوجبة (فطور/غداء/عشاء)
  cut: boolean; // هل تم قطع الوجبة
}

export interface CutMealDay {
  name: string; // اسم اليوم
  meals: CutMealItem[];
}
