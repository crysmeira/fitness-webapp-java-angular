package com.fitnesswebapp.utils;

/**
 * Constants used in Fitness Webapp for bean names.
 * 
 * @author Crystiane Meira
 */
public final class BeanNames {

	public BeanNames() {} // avoid instantiation
	
	// Nutritionix
	public static final String NUTRITIONIX_CONFIG = "nutritionixConfig";
	public static final String NUTRITIONIX_SERVICE = "nutritionixService";
	public static final String NUTRITIONIX_REPOSITORY = "nutritionixRepository";
	public static final String NUTRITIONIX_CONFIG_PROPS = "nutritionixConfigProps";
	
	// Nutritionix assembler
	public static final String NUTRIENT_MODEL_ASSEMBLER = "nutrientModelAssembler";
	public static final String EXERCISE_MODEL_ASSEMBLER = "exerciseModelAssembler";
	public static final String FOOD_MODEL_ASSEMBLER = "foodModelAssembler";

	// User
	public static final String USER_SERVICE = "userService";
	public static final String USER_INPUT_DISASSEMBLER = "userInputDisassembler";
	public static final String USER_MODEL_ASSEMBLER = "userModelAssembler";
	
	// Food diary entry
	public static final String FOOD_DIARY_ENTRY_SERVICE = "foodDiaryEntryService";
	public static final String FOOD_DIARY_ENTRY_INPUT_DISASSEMBLER = "foodDiaryEntryInputDisassembler";
	public static final String FOOD_DIARY_ENTRY_MODEL_ASSEMBLER = "foodDiaryEntryModelAssembler";

	// Exercise diary entry
	public static final String EXERCISE_DIARY_ENTRY_SERVICE = "exerciseDiaryEntryService";
	public static final String EXERCISE_DIARY_ENTRY_INPUT_DISASSEMBLER = "exerciseDiaryEntryInputDisassembler";
	public static final String EXERCISE_DIARY_ENTRY_MODEL_ASSEMBLER = "exerciseDiaryEntryModelAssembler";
	
	// Statistics
	public static final String STATISTICS_SERVICE = "statisticsService";
	
	// Message loader
	public static final String FITNESS_MESSAGE_LOADER = "fitnessMessageLoader";
	
}
