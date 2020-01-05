import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FoodDiaryService } from '../food-diary/food-diary.service';
import { ExerciseDiaryService } from '../exercise-diary/exercise-diary.service';

@Injectable()
export class TodayDiaryService {

    constructor(private http: HttpClient, private foodDiaryService: FoodDiaryService, private exerciseDiaryService: ExerciseDiaryService) { }

    getFoodDiaries() {
        const foodDiaries = this.foodDiaryService.getFoodDiaries();
    }

    getExerciseDiaries() {
        const exerciseDiaries = this.exerciseDiaryService.getExerciseDiaries();
    }
}