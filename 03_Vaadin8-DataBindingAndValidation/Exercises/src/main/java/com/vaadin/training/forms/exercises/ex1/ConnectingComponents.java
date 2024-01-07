package com.vaadin.training.forms.exercises.ex1;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;

public class ConnectingComponents extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	public ConnectingComponents() {
		// TODO Create a Slider component and add it to the layout
		// TODO Configure the Slider component (min- & max-value, width)
		Slider theSlider = new Slider(0, 100);
		theSlider.setWidth("300px");
		addComponent(theSlider);

		// TODO Create a Label component and add it to the layout
		// TODO The value of the slider should be displayed in the Label when
		// slider value changes
		Label theLabel = new Label("0.0");
		theLabel.setWidth("300px");
		addComponent(theLabel);

		theSlider.addValueChangeListener(event -> theLabel.setValue(String.valueOf(theSlider.getValue())));

		// TODO Create a ProgressBar component, add it to the layout, and set a
		// proper width for it
		ProgressBar theProgressBar = new ProgressBar(0.5f);
		theProgressBar.setWidth("300px");
		addComponent(theProgressBar);

		// TODO The value of the slider should be displayed in the progress
		// bar when slider value changes
		theSlider.addValueChangeListener(event -> theProgressBar.setValue(theSlider.getValue().floatValue() / 100.0f));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}
}
