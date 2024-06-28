package com.vaadin.training.theming.solutions.ex1;

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
import com.vaadin.training.theming.solutions.MainLayout;

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
		primaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		//The default style is secondary
		Button secondaryButton = new Button("Secondary");
		Button tertiaryButton = new Button("Tertiary");
		tertiaryButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		tertiaryButton.getElement().getThemeList().add("tertiary");
		add(new HorizontalLayout(primaryButton, secondaryButton, tertiaryButton));


		add(new Span("Radio button group"));
		RadioButtonGroup<String> group = new RadioButtonGroup<>();
		group.setItems("Option1", "Option2", "Option3");
		group.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
		add(group);


		add(new Span("Progress Bar"));
		ProgressBar contrastProgressBar = new ProgressBar();
		contrastProgressBar.addThemeVariants(ProgressBarVariant.LUMO_CONTRAST);
		contrastProgressBar.setValue(0.3);

		ProgressBar successProgressBar = new ProgressBar();
		successProgressBar.addThemeVariants(ProgressBarVariant.LUMO_SUCCESS);
		successProgressBar.setValue(0.4);

		ProgressBar errorProgressBar = new ProgressBar();
		errorProgressBar.setValue(0.5);
		errorProgressBar.addThemeVariants(ProgressBarVariant.LUMO_ERROR);
		add(contrastProgressBar, successProgressBar, errorProgressBar);

		add(new Span("TextField"));
		TextField alignCenterTextField = new TextField();
		alignCenterTextField.setValue("Align center");
		alignCenterTextField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
		TextField alignRightTextField = new TextField();
		alignRightTextField.setValue("Align right");
		alignRightTextField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
		add(new HorizontalLayout(alignCenterTextField, alignRightTextField));

	}
}