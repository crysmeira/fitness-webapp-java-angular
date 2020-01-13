import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FoodDiaryService } from './food-diary.service';
import { MatTableDataSource, MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';
import { MatPaginator } from '@angular/material/paginator';
import { FoodSearch } from './food-search.model';
import { FoodDiary } from '../food-diary.model';

export interface DialogData {
  amount: number;
}

@Component({
  selector: 'app-food-diary',
  templateUrl: './food-diary.component.html',
  styleUrls: ['./food-diary.component.css']
})
export class FoodDiaryComponent implements OnInit {

  foodEmpty: boolean = true;
  amount: number;

  foodResult: FoodSearch[] = [];
  displayedColumnsSearch: string[] = ['foodName', 'brandName'];
  dataSourceSearch = new MatTableDataSource<FoodSearch>(this.foodResult);

  foodDiaries: FoodDiary[] = [];
  displayedColumnsSummary: string[] = ['foodName', 'calories', 'carbohydrate', 'protein', 'fat'];
  dataSourceSummary = new MatTableDataSource<FoodDiary>(this.foodDiaries);

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  
  constructor(private foodDiaryService: FoodDiaryService,
              private _snackBar: MatSnackBar,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.dataSourceSearch.paginator = this.paginator;
  }

  onRowClicked(row) {
    const dialogRef = this.dialog.open(DialogFoodAmountDialog, {
      width: '250px',
      data: { amount: this.amount }
    });

    dialogRef.afterClosed().subscribe(amount => {
      if (amount) {
        this.foodDiaryService.searchNutrients(row.nixItemId)
                          .subscribe(nutrient => {
                            if (nutrient.servingWeightGrams) {
                              const finalCalories = (nutrient.calories * amount) / nutrient.servingWeightGrams;
                              const finalCarbohydrate = (nutrient.totalCarbohydrate * amount) / nutrient.servingWeightGrams;
                              const finalProtein = (nutrient.totalProtein * amount) / nutrient.servingWeightGrams;
                              const finalFat = (nutrient.totalFat * amount) / nutrient.servingWeightGrams;

                              this.foodDiaries.push(new FoodDiary(nutrient.foodName, finalCalories, finalCarbohydrate, finalProtein, finalFat));
                              this.dataSourceSummary = new MatTableDataSource<FoodDiary>(this.foodDiaries);
                            } else {
                              this.openSnackBar('Sorry, it was not possible to calculate the nutrition details.');
                            }
                          });
        }
    });
  }

  onSearchFood(form: NgForm) {
    this.foodDiaryService.searchFood(form.value.search)
                        .subscribe(result => {
                          this.foodResult = result;
                          this.dataSourceSearch = new MatTableDataSource<FoodSearch>(this.foodResult);
                          this.dataSourceSearch.paginator = this.paginator;
                          this.foodEmpty = false;
                        });
  }

  onSaveFoodDiary() {
    this.foodDiaryService.saveFoodDiaries(this.foodDiaries)
                      .subscribe(response => {
                        this.openSnackBar('Successfully saved food diary');
                      }, error => {
                        this.openSnackBar(error.error.detail);
                      });
    this.foodDiaries = [];
    this.dataSourceSummary = new MatTableDataSource<FoodDiary>(this.foodDiaries);
  }

  getTotalCalories() {
    return this.foodDiaries.map(f => f.calories).reduce((acc, value) => acc + value, 0);
  }

  getTotalCarbohydrate() {
    return this.foodDiaries.map(f => f.totalCarbohydrate).reduce((acc, value) => acc + value, 0);
  }

  getTotalProtein() {
    return this.foodDiaries.map(f => f.totalProtein).reduce((acc, value) => acc + value, 0);
  }

  getTotalFat() {
    return this.foodDiaries.map(f => f.totalFat).reduce((acc, value) => acc + value, 0);
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, null, {
      duration: 5000,
    });
  }
}

@Component({
  selector: 'dialog-food-amount-dialog',
  templateUrl: 'dialog-food-amount-dialog.html',
})
export class DialogFoodAmountDialog {

  constructor(public dialogRef: MatDialogRef<DialogFoodAmountDialog>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick() {
    this.dialogRef.close();
  }

}