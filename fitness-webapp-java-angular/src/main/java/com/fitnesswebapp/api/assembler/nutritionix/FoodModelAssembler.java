package com.fitnesswebapp.api.assembler.nutritionix;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.api.model.nutritionix.FoodModel;
import com.fitnesswebapp.domain.model.nutritionix.Food;
import com.fitnesswebapp.utils.BeanNames;

@Component(BeanNames.FOOD_MODEL_ASSEMBLER)
public class FoodModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FoodModel toModel(Food food) {
		return modelMapper.map(food, FoodModel.class);
	}
	
	public List<FoodModel> toCollectionModel(List<Food> food) {
		return food.stream()
				.map(f -> toModel(f))
				.collect(Collectors.toList());
	}
}
