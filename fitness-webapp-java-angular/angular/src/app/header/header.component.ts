import { Component } from '@angular/core';
import { TodayDiaryService } from '../diaries/today-diary/today-diary.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  collapsed = true;
  
  constructor(private todayDiaryService: TodayDiaryService) { }

  onGetTodayDiary() {
    this.todayDiaryService.getFoodDiaries();
    this.todayDiaryService.getExerciseDiaries();
  }

}
