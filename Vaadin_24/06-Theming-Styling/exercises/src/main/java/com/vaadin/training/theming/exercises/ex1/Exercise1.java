package com.vaadin.training.theming.exercises.ex1;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.training.theming.exercises.MainLayout;

import java.io.Serial;

@Route(value = Exercise1.ROUTE, layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class Exercise1 extends VerticalLayout {

	@Serial
	private static final long serialVersionUID = 1L;

	public static final String ROUTE = "ex1";
	public static final String TITLE = "Exercise 1";

	public Exercise1() {
		setSizeFull();

		add(new Span("Buttons"));
		Button primaryButton = new Button("Primary");
		//TODO add primary theme variant to primaryButton
		primaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		Button secondaryButton = new Button("Secondary");
		//TODO nothing, the default style is Secondary
		Button tertiaryButton = new Button("Tertiary");
		//TODO add tertiary theme variant to tertiaryButton
		tertiaryButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SMALL);
		add(new HorizontalLayout(primaryButton, secondaryButton, tertiaryButton));


		add(new Span("Radio button group"));
		RadioButtonGroup<String> group = new RadioButtonGroup<>();
		group.setItems("Option1", "Option2", "Option3");
		//TODO add vertical theme variant to group
		group.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
		add(group);


		add(new Span("Progress Bar"));
		ProgressBar contrastProgressBar = new ProgressBar();
		contrastProgressBar.setValue(0.3);
		//TODO add contrast theme variant to contrastProgressBar
		contrastProgressBar.addThemeVariants(ProgressBarVariant.LUMO_CONTRAST);

		ProgressBar successProgressBar = new ProgressBar();
		successProgressBar.setValue(0.4);
		//TODO add success theme variant to successProgressBar
		successProgressBar.addThemeVariants(ProgressBarVariant.LUMO_SUCCESS);

		ProgressBar errorProgressBar = new ProgressBar();
		errorProgressBar.setValue(0.5);
		//TODO add error theme variant to errorProgressBar
		errorProgressBar.addThemeVariants(ProgressBarVariant.LUMO_ERROR);

		add(contrastProgressBar, successProgressBar, errorProgressBar);

		add(new Span("TextField"));
		TextField alignCenterTextField = new TextField();
		alignCenterTextField.setValue("Align center");
		//TODO add align-center theme variant to alignCenterTextField
		alignCenterTextField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

		TextField alignRightTextField = new TextField();
		alignRightTextField.setValue("Align right");
		//TODO add align-right theme variant to alignRightTextField
		alignRightTextField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);

		add(new HorizontalLayout(alignCenterTextField, alignRightTextField));

	}
}