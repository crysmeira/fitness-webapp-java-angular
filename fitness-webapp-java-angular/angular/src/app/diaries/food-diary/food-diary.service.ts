import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FoodDiary } from '../food-diary.model';
import { FoodSearch } from './food-search.model';
import { Nutrient } from './nutrient.model';
import { AuthService } from 'src/app/auth/auth.service';

@Injectable()
export class FoodDiaryService {

    constructor(private http: HttpClient,
                private authService: AuthService) { }

    saveFoodDiaries(foodDiaries: FoodDiary[]) {
        const headers = new HttpHeaders({ authorization : sessionStorage.getItem(this.authService.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME) });
        const email = sessionStorage.getItem(this.authService.EMAIL_SESSION_ATTRIBUTE_NAME);
        
        return this.http.post('http://localhost:8080/food-diaries/' + email, foodDiaries, {headers});
    }

    getFoodDiaries() {
        const headers = new HttpHeaders({ authorization : sessionStorage.getItem(this.authService.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME) });
        const email = sessionStorage.getItem(this.authService.EMAIL_SESSION_ATTRIBUTE_NAME);

        return this.http.get<FoodDiary[]>('http://localhost:8080/food-diaries/' + email, {headers});
    }

    searchFood(search: string) {
        const headers = new HttpHeaders({ authorization : sessionStorage.getItem(this.authService.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME) });

        return this.http.get<FoodSearch[]>('http://localhost:8080/nutritionix/food/' + search, {headers});
    }

    searchNutrients(nixItemId: string) {
        const headers = new HttpHeaders({ authorization : sessionStorage.getItem(this.authService.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME) });

        return this.http.get<Nutrient>('http://localhost:8080/nutritionix/nutrients/' + nixItemId, {headers});
    }
}