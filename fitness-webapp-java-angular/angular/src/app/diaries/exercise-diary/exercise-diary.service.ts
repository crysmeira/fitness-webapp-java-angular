import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ExerciseDiaryService {

    constructor(private http: HttpClient) { }

    getExerciseDiaries() {
        console.log("Getting exercise diaries from backend");
        //const foodDiaries = this.http.get('http://localhost:8080/exercise-diaries')
        //                            .subscribe(foodDiaries => { console.log(exerciseDiaries); });
    }
}