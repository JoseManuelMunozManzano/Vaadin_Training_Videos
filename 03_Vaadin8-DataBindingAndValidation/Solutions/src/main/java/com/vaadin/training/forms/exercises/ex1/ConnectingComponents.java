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
		Slider slider = new Slider();
		addComponent(slider);

		slider.setMin(0d);
		slider.setMax(100d);
		slider.setWidth(300f, Unit.PIXELS);

		Label label = new Label();
		addComponent(label);

		slider.addValueChangeListener(event -> label.setValue(String.valueOf(slider.getValue())));

		ProgressBar progressBar = new ProgressBar();
		addComponent(progressBar);
		progressBar.setWidth(300f, Unit.PIXELS);

		slider.addValueChangeListener(event -> progressBar.setValue(slider.getValue().floatValue() / 100f));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}
}