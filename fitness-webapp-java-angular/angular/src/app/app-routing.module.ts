import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FoodDiaryComponent } from './diaries/food-diary/food-diary.component';
import { ExerciseDiaryComponent } from './diaries/exercise-diary/exercise-diary.component';
import { ProfileComponent } from './profile/profile.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { TodayDiaryComponent } from './diaries/today-diary/today-diary.component';
import { AuthComponent } from './auth/auth.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home.component';


const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'auth', component: AuthComponent},
  {path: 'food-diary', component: FoodDiaryComponent},
  {path: 'exercise-diary', component: ExerciseDiaryComponent},
  {path: 'today-diary', component: TodayDiaryComponent},
  {path: 'statistics', component: StatisticsComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'home', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
