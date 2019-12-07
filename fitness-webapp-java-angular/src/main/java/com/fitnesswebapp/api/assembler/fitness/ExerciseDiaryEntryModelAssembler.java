package com.fitnesswebapp.api.assembler.fitness;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.api.model.fitness.ExerciseDiaryEntryModel;
import com.fitnesswebapp.domain.model.fitness.ExerciseDiaryEntry;

@Component
public class ExerciseDiaryEntryModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ExerciseDiaryEntryModel toModel(ExerciseDiaryEntry exerciseDiaryEntry) {
		return modelMapper.map(exerciseDiaryEntry, ExerciseDiaryEntryModel.class);
	}
	
	public List<ExerciseDiaryEntryModel> toCollectionModel(List<ExerciseDiaryEntry> exerciseDiaryEntryList) {
		return exerciseDiaryEntryList.stream()
				.map(e -> toModel(e))
				.collect(Collectors.toList());
	}
}
