import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';

import { ExerciseDiary } from '../exercise-diary.model';
import { FoodDiary } from '../food-diary.model';
import { FoodDiaryService } from '../food-diary/food-diary.service';
import { ExerciseDiaryService } from '../exercise-diary/exercise-diary.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-today-diary',
  templateUrl: './today-diary.component.html',
  styleUrls: ['./today-diary.component.css']
})
export class TodayDiaryComponent implements OnInit {

  exerciseDiaryEntries: ExerciseDiary[] = [];
  foodDiaryEntries: FoodDiary[] = [];

  displayedColumnsExercise: string[] = ['exerciseName', 'duration', 'calories'];
  dataSourceExercise = new MatTableDataSource<ExerciseDiary>(this.exerciseDiaryEntries);

  displayedColumnsFood: string[] = ['foodName', 'calories', 'carbohydrate', 'protein', 'fat'];
  dataSourceFood = new MatTableDataSource<FoodDiary>(this.foodDiaryEntries);

  constructor(private foodDiaryService: FoodDiaryService,
              private exerciseDiaryService: ExerciseDiaryService,
              private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.foodDiaryService.getFoodDiaries()
                        .subscribe(result => {
                          this.foodDiaryEntries = result;
                          this.dataSourceFood = new MatTableDataSource<FoodDiary>(this.foodDiaryEntries);
                        }, error => {
                          this.openSnackBar('There was an error retrieving food diaries for today');
                        });

    this.exerciseDiaryService.getExerciseDiaries()
                            .subscribe(result => {
                              this.exerciseDiaryEntries = result;
                              this.dataSourceExercise = new MatTableDataSource<ExerciseDiary>(this.exerciseDiaryEntries);
                            }, error => {
                              this.openSnackBar('There was an error retrieving exercise diaries for today');
                            });
  }

  getTotalCalories() {
    return this.foodDiaryEntries.map(f => f.calories).reduce((acc, value) => acc + value, 0);
  }

  getTotalCarbohydrate() {
    return this.foodDiaryEntries.map(f => f.totalCarbohydrate).reduce((acc, value) => acc + value, 0);
  }

  getTotalProtein() {
    return this.foodDiaryEntries.map(f => f.totalProtein).reduce((acc, value) => acc + value, 0);
  }

  getTotalFat() {
    return this.foodDiaryEntries.map(f => f.totalFat).reduce((acc, value) => acc + value, 0);
  }

  getTotalDuration() {
    return this.exerciseDiaryEntries.map(f => f.duration).reduce((acc, value) => acc + value, 0);
  }

  getTotalCaloriesBurned() {
    return this.exerciseDiaryEntries.map(f => f.calories).reduce((acc, value) => acc + value, 0);
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, null, {
      duration: 4000,
    });
  }
}
