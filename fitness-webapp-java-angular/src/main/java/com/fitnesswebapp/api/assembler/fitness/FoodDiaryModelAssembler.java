package com.fitnesswebapp.api.assembler.fitness;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.api.model.fitness.FoodDiaryModel;
import com.fitnesswebapp.domain.model.fitness.FoodDiary;

@Component
public class FoodDiaryModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FoodDiaryModel toModel(FoodDiary foodDiary) {
		return modelMapper.map(foodDiary, FoodDiaryModel.class);
	}
}
