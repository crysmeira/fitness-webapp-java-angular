package com.fitnesswebapp.core.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.utils.BeanNames;

import lombok.Data;

/**
 * Loads the properties used as headers in order to make requests to Nutritionix API.
 *
 * @author Crystiane Meira
 */
@Data
@Component(BeanNames.NUTRITIONIX_CONFIG_PROPS)
@ConfigurationProperties(prefix = "nutritionix")
public class NutritionixConfigProps {

	private String appId;

	private String appKey;

	private String remoteUserId;

}
