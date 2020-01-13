import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ExerciseDiary } from '../exercise-diary.model';
import { ExerciseDiaryService } from './exercise-diary.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-exercise-diary',
  templateUrl: './exercise-diary.component.html',
  styleUrls: ['./exercise-diary.component.css']
})
export class ExerciseDiaryComponent {

  public exercises = [
    "Running", 
    "Walking", 
    "Swimming",
    "Dancing",
    "Hiking",
    "Biking"
  ];
  public selectedExercise = null;

  constructor(private exerciseDiaryService: ExerciseDiaryService,
              private _snackBar: MatSnackBar) { }

  onSaveExerciseDiary(form: NgForm) {
    const value = form.value;
    const exercise = new ExerciseDiary(value.exercise, value.duration, 0);

    this.exerciseDiaryService.getCaloriesBurned(exercise)
                                  .subscribe(exerciseDiary => {
                                    this.exerciseDiaryService.saveExerciseDiary(exerciseDiary);
                                    this.openSnackBar('Successfully saved exercise diary');
                                  }, error => {
                                    this.openSnackBar(error.error.detail);
                                  });

    form.reset();
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, null, {
      duration: 3000,
    });
  }
}
