package com.fitnesswebapp.api.assembler.fitness;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.api.model.fitness.FoodDiaryEntryModel;
import com.fitnesswebapp.domain.model.fitness.FoodDiaryEntry;

@Component
public class FoodDiaryEntryModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FoodDiaryEntryModel toModel(FoodDiaryEntry foodDiaryEntry) {
		return modelMapper.map(foodDiaryEntry, FoodDiaryEntryModel.class);
	}
}
