package com.vaadin.training.forms.exercises.ex1;

import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.training.forms.exercises.MainLayout;

import java.io.Serial;

@Route(value = Validation.ROUTE, layout = MainLayout.class)
@RouteAlias(value="", layout = MainLayout.class)
public class Validation extends VerticalLayout implements HasSize{

	@Serial
	private static final long serialVersionUID = 1L;

	public static final String ROUTE = "ex1";
	public static final String TITLE = "Validation";

	public Validation() {
		// TODO Create a bean class to contain the data with fields, getters and
		// setters for each value needed to bind the three TextFields.

		// TODO Create a Binder typed for the bean class you just created
		final Binder<FormBean> binder = new Binder<>(FormBean.class);
		binder.setBean(new FormBean());

		Paragraph errorHolder = new Paragraph();

		// TODO Bind field and add validation to check that the input is a
		// proper mail address.
		final TextField emailField = new TextField("Email validator");
		binder.forField(emailField)
				.withValidator(new EmailValidator("Are you sure the given value is an email address?"))
				.withStatusLabel(errorHolder)
				.bind(FormBean::getEmail, FormBean::setEmail);

		// TODO Bind field and add validation which accepts strings between 1
		// and 10 in length
		final TextField stringField = new TextField("String length validator");
		binder.forField(stringField)
				.withValidator(new StringLengthValidator(
						"Maximum of 10 characters allowed",1, 10))
				.withStatusLabel(errorHolder)
				.bind(FormBean::getStringField, FormBean::setStringField);

		// TODO Bind field and add a custom Validator which only accepts
		// "Vaadin"
		final TextField vaadinField = new TextField("Vaadin validator");
		binder.forField(vaadinField)
				// Usando validator personalizado
//				.withValidator(new VaadinValidator())
				// Otra forma
				.withValidator((value, context) -> {
					if (value == null || value.isEmpty() || value.equals("Vaadin")) {
						return ValidationResult.ok();
					}

					return ValidationResult.error(value + " not accepted");
				})
				.withStatusLabel(errorHolder)
				.bind(FormBean::getVaadinString, FormBean::setVaadinString);

		add(errorHolder, emailField, stringField, vaadinField);

	}

}