package com.fitnesswebapp.core.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The annotated element cannot have its value updated.
 * 
 * @author Crystiane Meira
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface NotUpdatable {

}
