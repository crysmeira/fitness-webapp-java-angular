import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';

import { FoodDiary } from '../../food-diary.model';

@Component({
  selector: 'app-food-diary-summary',
  templateUrl: './food-diary-summary.component.html',
  styleUrls: ['./food-diary-summary.component.css']
})
export class FoodDiarySummaryComponent implements OnInit {

  foodDiaries: FoodDiary[] = [
    new FoodDiary(1, 'Rice', 98, 120, 10, 3),
    new FoodDiary(2, 'Beans', 98, 120, 10, 3),
    new FoodDiary(3, 'Pasta', 98, 120, 10, 3),
    new FoodDiary(1, 'Rice', 98, 120, 10, 3),
    new FoodDiary(2, 'Beans', 98, 120, 10, 3),
    new FoodDiary(3, 'Pasta', 98, 120, 10, 3),
    new FoodDiary(1, 'Rice', 98, 120, 10, 3),
    new FoodDiary(2, 'Beans', 98, 120, 10, 3),
    new FoodDiary(3, 'Pasta', 98, 120, 10, 3),
    new FoodDiary(1, 'Rice', 98, 120, 10, 3),
    new FoodDiary(2, 'Beans', 98, 120, 10, 3),
    new FoodDiary(3, 'Pasta', 98, 120, 10, 3)
  ];

  displayedColumns: string[] = ['id', 'foodName', 'calories', 'carbohydrate', 'protein', 'fat'];
  dataSource = new MatTableDataSource<FoodDiary>(this.foodDiaries);

  constructor() { }

  ngOnInit() {
  }

  onRowClicked(row) {
    console.log('Row clicked: ', row);
  }

}
