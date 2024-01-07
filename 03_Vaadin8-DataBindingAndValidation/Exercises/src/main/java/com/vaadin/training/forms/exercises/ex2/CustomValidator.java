package com.vaadin.training.forms.exercises.ex2;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;

public class CustomValidator implements Validator<String> {

  @Override
  public ValidationResult apply(String value, ValueContext context) {
    if (value.equals("Vaadin")) {
      return ValidationResult.ok();
    }

    return ValidationResult.error("The Test Must Be Vaadin");
  }
  
}
