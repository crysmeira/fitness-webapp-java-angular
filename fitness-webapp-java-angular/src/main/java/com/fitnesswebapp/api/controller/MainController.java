package com.fitnesswebapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@RequestMapping(value = "/**")
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public void methodNotAllowed() {
	}
	
}
