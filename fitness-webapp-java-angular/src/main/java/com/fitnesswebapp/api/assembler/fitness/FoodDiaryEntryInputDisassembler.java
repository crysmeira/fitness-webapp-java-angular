package com.fitnesswebapp.api.assembler.fitness;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.api.model.fitness.input.FoodDiaryEntryInput;
import com.fitnesswebapp.domain.model.fitness.FoodDiaryEntry;
import com.fitnesswebapp.utils.BeanNames;

@Component(BeanNames.FOOD_DIARY_ENTRY_INPUT_DISASSEMBLER)
public class FoodDiaryEntryInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FoodDiaryEntry toDomainObject(FoodDiaryEntryInput foodDiaryEntryInput) {
		return modelMapper.map(foodDiaryEntryInput, FoodDiaryEntry.class);
	}
	
	public List<FoodDiaryEntry> toCollectionDomainObject(List<FoodDiaryEntryInput> foodDiaryEntryInputList) {
		return foodDiaryEntryInputList.stream()
				.map(f -> toDomainObject(f))
				.collect(Collectors.toList());
	}
}
