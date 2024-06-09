package com.vaadin.training.dataproviders.exercises;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.training.dataproviders.exercises.ex1.CreateDataProvider;
import com.vaadin.training.dataproviders.exercises.ex2.FilteringDataProvider;
import com.vaadin.training.dataproviders.exercises.ex3.BackEndDataProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	private final Navigator navigator;

	public MainView(Navigator navigator) {
		this.navigator = navigator;
		setSpacing(true);

		int i = 1;
		createExerciseLink(i++, "Create a DataProvider", CreateDataProvider.class);
		createExerciseLink(i++, "Filter a DataProvider", FilteringDataProvider.class);
		createExerciseLink(i++, "Filter a BackEndDataProvider", BackEndDataProvider.class);
	}

	private void createExerciseLink(int exNum, String caption, Class<? extends View> viewClass) {
		navigator.addView(caption.replace(" ", ""), viewClass);
		final Button button = new Button("Ex " + exNum + ": " + caption,
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