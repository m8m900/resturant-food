export interface Reservation {
  id?: number;
  orderType: string; // إفطار / غداء / عشاء
  reservationTime: string; // ISO datetime
  endTime: string; // ISO datetime
}
