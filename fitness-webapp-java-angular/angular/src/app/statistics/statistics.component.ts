import { Component, ViewChild } from '@angular/core';
import { ChartDataSets } from 'chart.js';
import { BaseChartDirective, Label } from 'ng2-charts';
import { StatisticsService } from './statistics.service';
import { NgForm } from '@angular/forms';
import { ChartData } from './chart-data.model';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent {

  public periods = [
    { name: "1 day", value: 1 }, 
    { name: "7 days", value: 7 },
    { name: "30 days", value: 30 },
    { name: "180 days", value: 180 },
    { name: "365 days", value: 365 },
  ];
  public selectedPeriod = null;

  public exercisesCaloriesBurnedData: ChartDataSets[] = [];
  public exercisesDurationData: ChartDataSets[] = [];
  public exercisesLabels: Label[] = [];

  public foodCaloriesData: ChartDataSets[] = [];
  public foodFatData: ChartDataSets[] = [];
  public foodProteinData: ChartDataSets[] = [];
  public foodCarbohydrateData: ChartDataSets[] = [];
  public foodLabels: Label[] = [];

  public nutrientsData: ChartDataSets[] = [];
  public nutrientsLabels: Label[] = [];

  public lineChartType = 'line';
  public barChartType = 'bar';

  @ViewChild(BaseChartDirective, { static: true }) chart: BaseChartDirective;

  constructor(private statisticsService: StatisticsService) { }

  onLoadStatistics(form: NgForm) {
    const value = form.value;

    this.cleanUpData();

    this.statisticsService.loadStatistics(value.period)
                      .subscribe(statistics => {
                        this.loadFoodData(statistics);
                        this.loadNutritionData(statistics);
                        this.loadExerciseData(statistics);
                    });
  }

  cleanUpData() {
    this.exercisesCaloriesBurnedData = [];
    this.exercisesDurationData = [];
    this.exercisesLabels = [];

    this.foodCaloriesData = [];
    this.foodFatData = [];
    this.foodProteinData = [];
    this.foodCarbohydrateData = [];
    this.foodLabels = [];

    this.nutrientsData = [];
    this.nutrientsLabels = [];
  }

  loadFoodData(statistics: Object) {
    const foodCaloriesValues: number[] = [];
    const foodCarbohydrateValues: number[] = [];
    const foodProteinValues: number[] = [];
    const foodFatValues: number[] = [];
    for (let key in statistics['foodData']) {
        let value = statistics['foodData'][key];
        this.foodLabels.push(key);

        foodCaloriesValues.push(value['calories']);
        foodCarbohydrateValues.push(value['carbohydrate']);
        foodProteinValues.push(value['protein']);
        foodFatValues.push(value['fat']);
    }

    this.foodCaloriesData.push(new ChartData(foodCaloriesValues, 'Calories'));
    this.foodCarbohydrateData.push(new ChartData(foodCarbohydrateValues, 'Carbohydrate'));
    this.foodProteinData.push(new ChartData(foodProteinValues, 'Protein'));
    this.foodFatData.push(new ChartData(foodFatValues, 'Fat'));
  }

  loadNutritionData(statistics: Object) {
    const nutrientsCarbohydrate: number[] = [];
    const nutrientsProtein: number[] = [];
    const nutrientsFat: number[] = [];
    for (let key in statistics['foodData']) {
        let value = statistics['foodData'][key];
        this.nutrientsLabels.push(key);

        nutrientsCarbohydrate.push(value['carbohydrate']);
        nutrientsProtein.push(value['protein']);
        nutrientsFat.push(value['fat']);
    }

    this.nutrientsData.push(new ChartData(nutrientsCarbohydrate, 'Carbohydrate'));
    this.nutrientsData.push(new ChartData(nutrientsProtein, 'Protein'));
    this.nutrientsData.push(new ChartData(nutrientsFat, 'Fat'));
  }

  loadExerciseData(statistics: Object) {
    const exerciseCaloriesBurnedValues: number[] = [];
    const exerciseDurationValues: number[] = [];
    for (let key in statistics['exerciseData']) {
        let value = statistics['exerciseData'][key];
        this.exercisesLabels.push(key);

        exerciseCaloriesBurnedValues.push(value['calories']);
        exerciseDurationValues.push(value['duration']);
    }

    this.exercisesCaloriesBurnedData.push(new ChartData(exerciseCaloriesBurnedValues, 'Calories burned'));
    this.exercisesDurationData.push(new ChartData(exerciseDurationValues, 'Duration'));
  }
}
