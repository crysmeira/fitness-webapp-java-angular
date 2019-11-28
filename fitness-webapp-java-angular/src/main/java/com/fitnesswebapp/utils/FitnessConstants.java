package com.fitnesswebapp.utils;

/**
 * Constants used in Fitness Webapp.
 *
 * @author Crystiane Meira
 */
public class FitnessConstants {

	private FitnessConstants() {} // avoid instantiation

	public static final String NUTRITIONIX_CONFIG_BEAN = "nutritionixConfig";
	public static final String NUTRITIONIX_CONFIG_PROPS = "nutritionixConfigProps";

	public static final String NUTRITIONIX_SERVICE_BEAN = "nutritionixService";
	public static final String USER_SERVICE_BEAN = "userService";

	public static final String NUTRITIONIX_REPOSITORY_BEAN = "nutritionixRepository";

	public static final String SLASH = "/";

	public static final String NUTRITIONIX_BASE_URL = "https://trackapi.nutritionix.com";
	public static final String NUTRITIONIX_FOOD_QUERY = "v2/search/instant?query=";
	public static final String NUTRITIONIX_NUTRIENT_QUERY = "v2/search/item?nix_item_id=";
	public static final String NUTRITIONIX_EXERCISE_QUERY = "v2/natural/exercise";
	public static final String NUTRITIONIX_X_APP_ID_HEADER = "x-app-id";
	public static final String NUTRITIONIX_X_APP_KEY_HEADER = "x-app-key";
	public static final String NUTRITIONIX_X_REMOTE_USER_ID_HEADER = "x-remote-user-id";

	public static final String NUTRITIONIX_FOOD_BRANDED_ARRAY = "branded";
	public static final String NUTRITIONIX_NUTRITION_FOODS_ARRAY = "foods";
	public static final String NUTRITIONIX_EXERCISES_ARRAY = "exercises";

}
