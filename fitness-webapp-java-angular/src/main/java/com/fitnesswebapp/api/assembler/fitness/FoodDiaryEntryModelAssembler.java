package com.fitnesswebapp.api.assembler.fitness;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	
	public List<FoodDiaryEntryModel> toCollectionModel(List<FoodDiaryEntry> foodDiaryEntries) {
		return foodDiaryEntries.stream()
				.map(f -> toModel(f))
				.collect(Collectors.toList());
	}
}
