package com.fitnesswebapp.api.assembler.nutritionix;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.api.model.nutritionix.ExerciseModel;
import com.fitnesswebapp.domain.model.nutritionix.Exercise;
import com.fitnesswebapp.utils.BeanNames;

@Component(BeanNames.EXERCISE_MODEL_ASSEMBLER)
public class ExerciseModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ExerciseModel toModel(Exercise exercise) {
		return modelMapper.map(exercise, ExerciseModel.class);
	}
}
