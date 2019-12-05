package com.fitnesswebapp.api.assembler.fitness;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.api.model.fitness.input.UserInput;
import com.fitnesswebapp.domain.model.fitness.User;

@Component
public class UserInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public User toDomainObject(UserInput userInput) {
		return modelMapper.map(userInput, User.class);
	}
}
