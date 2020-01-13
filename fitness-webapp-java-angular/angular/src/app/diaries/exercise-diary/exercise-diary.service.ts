import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ExerciseDiary } from '../exercise-diary.model';
import { AuthService } from 'src/app/auth/auth.service';

@Injectable()
export class ExerciseDiaryService {

    constructor(private http: HttpClient,
                private authService: AuthService) { }
    
    getExerciseDiaries() {
        const headers = new HttpHeaders({ authorization : sessionStorage.getItem(this.authService.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME) });
        const email = sessionStorage.getItem(this.authService.EMAIL_SESSION_ATTRIBUTE_NAME);

        return this.http.get<ExerciseDiary[]>('http://localhost:8080/exercise-diaries/' + email, {headers});
    }

    saveExerciseDiary(exerciseDiary: ExerciseDiary) {
        const headers = new HttpHeaders({ authorization : sessionStorage.getItem(this.authService.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME) });
        const email = sessionStorage.getItem(this.authService.EMAIL_SESSION_ATTRIBUTE_NAME);

        this.http.post('http://localhost:8080/exercise-diaries/' + email, exerciseDiary, {headers}).subscribe();
    }

    getCaloriesBurned(exerciseDiary: ExerciseDiary) {
        const headers = new HttpHeaders({ authorization : sessionStorage.getItem(this.authService.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME) });
        const email = sessionStorage.getItem(this.authService.EMAIL_SESSION_ATTRIBUTE_NAME);

        return this.http.get<ExerciseDiary>('http://localhost:8080/nutritionix/exercises/' + exerciseDiary.exercise + '/' + exerciseDiary.duration + '/' + email, {headers});
    }
}