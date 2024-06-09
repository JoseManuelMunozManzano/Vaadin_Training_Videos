package com.vaadin.training.forms.exercises.ex2;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class Validation extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public Validation() {

		// TODO Create a value container bean class with fields, getters and
		// setters for each value needed to bind the five TextFields
		
		// TODO Create a binder typed for the value container bean
		Binder<TextFieldBean> binder = new Binder<>(TextFieldBean.class);

		// Bind an actual concrete TextFieldBean instance.
		// After this, whenever the user changes the value
		// of a field, p.setXxxxx is automatically called.
		binder.setBean(new TextFieldBean());
		
		// TODO Add validation for a double in range of 1 to 100
		TextField doubleField = new TextField("Double range validator");
		binder.forField(doubleField).withNullRepresentation("")
			.withConverter(new StringToDoubleConverter("Must Enter a Number"))
			.withValidator(new DoubleRangeValidator("Range between 1 and 100", 1.0, 100.0))
			// Si queremos usar el nombre del campo así, tenemos que indicar en new Binder el bean
			.bind("myDouble");

		// TODO Add validation for an integer in range of 1 to 100
		TextField integerField = new TextField("Integer range validator");
		binder.forField(integerField).withNullRepresentation("")
			.withConverter(new StringToIntegerConverter("Must Enter a Number"))
			.withValidator(new IntegerRangeValidator("Range between 1 and 100", 1, 100))
			// Aquí indicamos la referencia getter y setter
			.bind(TextFieldBean::getMyInt, TextFieldBean::setMyInt);

		// TODO Add validation to check that the input is a proper mail address
		TextField emailField = new TextField("Email validator");
		binder.forField(emailField)
			.withValidator(new EmailValidator("Email not Valid"))
			.bind(TextFieldBean::getEmail, TextFieldBean::setEmail);

		// TODO Add validation which accepts strings between 1 and 10 in length
		TextField stringField = new TextField("String length validator");
		binder.forField(stringField)
		// Dos formas de hacer esta validación
			.withValidator(text -> text.length() >= 1 && text.length() <= 10, "Length of the String between 1 and 10")
			// .withValidator(new StringLengthValidator("Length of the String between 1 and 10", 1, 10))
			.bind(TextFieldBean::getLengthString, TextFieldBean::setLengthString);

		// TODO Add a custom Validator which only accepts "Vaadin"
		TextField vaadinField = new TextField("Vaadin validator");
		binder.forField(vaadinField)
			// Dos formas de hacer el CustomValidator
			// .withValidator(new CustomValidator())
			.withValidator((value, context) -> {
				if (value == null || "".equals(value) || value.equals("Vaadin")) {
					return ValidationResult.ok();
				}

				return ValidationResult.error("Not accepted");
			})
			.bind(TextFieldBean::getVaadin, TextFieldBean::setVaadin);

		addComponents(doubleField, integerField, emailField, stringField, vaadinField);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}
}
