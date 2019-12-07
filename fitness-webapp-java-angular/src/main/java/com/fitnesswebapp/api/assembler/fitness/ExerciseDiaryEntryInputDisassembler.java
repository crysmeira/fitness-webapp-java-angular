package com.fitnesswebapp.api.assembler.fitness;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.api.model.fitness.input.ExerciseDiaryEntryInput;
import com.fitnesswebapp.domain.model.fitness.ExerciseDiaryEntry;

@Component
public class ExerciseDiaryEntryInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ExerciseDiaryEntry toDomainObject(ExerciseDiaryEntryInput exerciseDiaryEntryInput) {
		return modelMapper.map(exerciseDiaryEntryInput, ExerciseDiaryEntry.class);
	}
	
}
