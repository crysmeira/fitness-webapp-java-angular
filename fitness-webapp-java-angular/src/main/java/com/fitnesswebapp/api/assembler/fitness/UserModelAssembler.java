package com.fitnesswebapp.api.assembler.fitness;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.api.model.fitness.UserModel;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.utils.BeanNames;

@Component(BeanNames.USER_MODEL_ASSEMBLER)
public class UserModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserModel toModel(User user) {
		return modelMapper.map(user, UserModel.class);
	}
}
