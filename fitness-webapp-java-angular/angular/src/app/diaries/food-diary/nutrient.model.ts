export class Nutrient {
    constructor(public nixItemId: string, public foodName: string, public servingUnit: string, public servingQuantity: string,
        public servingWeightGrams: number, public calories: number, public totalFat: number, public totalCarbohydrate: number, public totalProtein: number) { }
}