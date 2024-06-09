package com.vaadin.training.spring;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = "second")
public class SecondView extends VerticalLayout implements View {

	public SecondView() {
		addComponent(new Label("Second view"));
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}
}
