package com.fitnesswebapp.api.assembler.nutritionix;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.api.model.nutritionix.NutrientModel;
import com.fitnesswebapp.domain.model.nutritionix.Nutrient;
import com.fitnesswebapp.utils.BeanNames;

@Component(BeanNames.NUTRIENT_MODEL_ASSEMBLER)
public class NutrientModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public NutrientModel toModel(Nutrient nutrient) {
		return modelMapper.map(nutrient, NutrientModel.class);
	}
}
