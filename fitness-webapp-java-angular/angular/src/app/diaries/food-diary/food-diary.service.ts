import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class FoodDiaryService {

    constructor(private http: HttpClient) { }

    getFoodDiaries() {
        console.log("Getting food diaries from backend");
        //const foodDiaries = this.http.get('http://localhost:8080/food-diaries')
        //                            .subscribe(foodDiaries => { console.log(foodDiaries); });
    }
}