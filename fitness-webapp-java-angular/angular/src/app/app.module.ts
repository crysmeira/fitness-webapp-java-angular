import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatTableModule, MatInputModule, MatButtonModule, MatPaginatorModule } from '@angular/material';
import { MatCardModule, MatNativeDateModule } from '@angular/material';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatExpansionModule } from '@angular/material/expansion';
import { ChartsModule } from 'ng2-charts';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FoodDiaryComponent } from './diaries/food-diary/food-diary.component';
import { ExerciseDiaryComponent } from './diaries/exercise-diary/exercise-diary.component';
import { TodayDiaryComponent } from './diaries/today-diary/today-diary.component';
import { HeaderComponent } from './header/header.component';
import { ProfileComponent } from './profile/profile.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { FoodDiarySummaryComponent } from './diaries/food-diary/food-diary-summary/food-diary-summary.component';
import { FoodDiaryQueryResultComponent } from './diaries/food-diary/food-diary-query-result/food-diary-query-result.component';
import { AuthComponent } from './auth/auth.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { DropdownDirective } from './dropdown.directive';
import { ProfileService } from './profile/profile.service';
import { FoodDiaryService } from './diaries/food-diary/food-diary.service';
import { ExerciseDiaryService } from './diaries/exercise-diary/exercise-diary.service';
import { TodayDiaryService } from './diaries/today-diary/today-diary.service';
import { StatisticsService } from './statistics/statistics.service';
import { AuthService } from './auth/auth.service';

@NgModule({
  declarations: [
    AppComponent,
    FoodDiaryComponent,
    ExerciseDiaryComponent,
    TodayDiaryComponent,
    HeaderComponent,
    ProfileComponent,
    FoodDiarySummaryComponent,
    FoodDiaryQueryResultComponent,
    AuthComponent,
    StatisticsComponent,
    DropdownDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatTableModule,
    NoopAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatPaginatorModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    FormsModule,
    MatExpansionModule,
    ChartsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    ProfileService,
    FoodDiaryService,
    ExerciseDiaryService,
    TodayDiaryService,
    StatisticsService,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
