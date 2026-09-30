import { Component } from '@angular/core';
import { TableModule } from 'primeng/table';
import { RestaurantCard } from '../../models/restaurant-card';

@Component({
  imports: [TableModule],
  selector: 'app-restaurant-list',
  styleUrl: './restaurant-list.css',
  templateUrl: './restaurant-list.html',
})
export class RestaurantList {
  // TODO: استبدال هذي القائمة باستدعاء REST API الفعلي (لاحقاً)
  restaurants: RestaurantCard[] = [
    { id: 1, name: 'مطعم الأمل', site: 'المبنى A' },
    { id: 2, name: 'مطعم النخيل', site: 'المبنى B' },
  ];
}
