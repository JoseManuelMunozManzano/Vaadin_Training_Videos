package com.vaadin.training.forms.exercises.ex1;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class VaadinValidator implements Validator<String> {

    @Override
    public ValidationResult apply(String s, ValueContext valueContext) {
        if (s.equalsIgnoreCase("vaadin")) {
            return ValidationResult.ok();
        }

        return ValidationResult.error(s + " not accepted");
    }
}
