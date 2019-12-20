import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';

import { ExerciseDiary } from '../exercise-diary.model';

@Component({
  selector: 'app-today-diary',
  templateUrl: './today-diary.component.html',
  styleUrls: ['./today-diary.component.css']
})
export class TodayDiaryComponent implements OnInit {

  exerciseDiaryEntries: ExerciseDiary[] = [
    new ExerciseDiary(1, 'Running', 98, 120),
    new ExerciseDiary(2, 'Walking', 98, 120),
    new ExerciseDiary(3, 'Dancing', 98, 120),
    new ExerciseDiary(4, 'Swimming', 98, 120),
    new ExerciseDiary(5, 'Hiking', 98, 120),
    new ExerciseDiary(6, 'Biking', 98, 120)
  ];

  displayedColumns: string[] = ['exerciseName', 'duration', 'calories'];
  dataSourceExercise = new MatTableDataSource<ExerciseDiary>(this.exerciseDiaryEntries);
  
  constructor() { }

  ngOnInit() {
  }

}
