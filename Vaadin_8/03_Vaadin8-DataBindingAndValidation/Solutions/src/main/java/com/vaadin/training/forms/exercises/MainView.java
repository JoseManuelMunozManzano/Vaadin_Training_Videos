package com.vaadin.training.forms.exercises;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.training.forms.exercises.ex1.ConnectingComponents;
import com.vaadin.training.forms.exercises.ex2.Validation;
import com.vaadin.training.forms.exercises.ex3.BindingForms;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	private Navigator navigator;

	public MainView(Navigator navigator) {
		this.navigator = navigator;
		setSpacing(true);

		int i = 1;
		createExerciseLink(i++, "Connecting Components", ConnectingComponents.class);
		createExerciseLink(i++, "Validation", Validation.class);
		createExerciseLink(i++, "Binding Forms with Data", BindingForms.class);
	}

	private void createExerciseLink(int exNum, String caption, Class<? extends View> viewClass) {
		navigator.addView(caption.replace(" ", ""), viewClass);
		Button button = new Button("Ex " + exNum + ": " + caption,
				event -> navigator.navigateTo(event.getButton().getData().toString().replace(" ", "")));
		button.setData(caption);
		button.setStyleName(ValoTheme.BUTTON_LINK);

		addComponent(button);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, no need to do anything here.
	}
}