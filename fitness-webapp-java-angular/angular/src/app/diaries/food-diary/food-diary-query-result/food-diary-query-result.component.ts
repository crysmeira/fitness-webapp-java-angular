import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

import { FoodDiary } from '../../food-diary.model';

@Component({
  selector: 'app-food-diary-query-result',
  templateUrl: './food-diary-query-result.component.html',
  styleUrls: ['./food-diary-query-result.component.css']
})
export class FoodDiaryQueryResultComponent implements OnInit {

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

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  
  constructor() { }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

  onRowClicked(row) {
    console.log('Row clicked: ', row);
  }

}
