package com.vaadin.training.forms.exercises.ex2;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class Validation extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public Validation() {
		// We need to provide the bean class reference in the constructor in
		// order to be able to use binding by property names (done for
		// doubleField in this example)
		Binder<FormValue> binder = new Binder<>(FormValue.class);

		binder.setBean(new FormValue());

		TextField doubleField = new TextField("Double range validator");
		binder.forField(doubleField).withNullRepresentation("")
				.withConverter(new StringToDoubleConverter("Value could not be converted to a Double"))
				.withValidator(new DoubleRangeValidator("Value must be a Double between 1 and 100", 1d, 100d))
				// Note: Here we use the field name from FormValue class for
				// binding
				.bind("doubleValue");

		TextField integerField = new TextField("Integer range validator");
		binder.forField(integerField).withNullRepresentation("")
				.withConverter(new StringToIntegerConverter("Value could not be converted to an Integer"))
				.withValidator(new IntegerRangeValidator("Value must be an Integer and between 1 and 100", 1, 100))
				// Note: Here we provide a getter and setter for binding
				.bind(FormValue::getIntegerValue, FormValue::setIntegerValue);

		TextField emailField = new TextField("Email validator");
		binder.forField(emailField)
				.withValidator(new EmailValidator("Are you sure the given value is an email address"))
				.bind(FormValue::getEmailValue, FormValue::setEmailValue);

		TextField stringField = new TextField("String length validator");
		binder.forField(stringField).withValidator(new StringLengthValidator("Maximum of 10 characters allowed", 0, 10))
				.bind(FormValue::getStringValue, FormValue::setStringValue);

		TextField vaadinField = new TextField("Vaadin validator");
		binder.forField(vaadinField).withValidator((value, context) -> {
			if (value == null || "".equals(value) || value.equals("Vaadin")) {
				return ValidationResult.ok();
			} else {
				return ValidationResult.error("{0} not accepted");
			}
		}).bind(FormValue::getVaadinValue, FormValue::setVaadinValue);

		addComponents(doubleField, integerField, emailField, stringField, vaadinField);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}
}