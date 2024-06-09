package com.vaadin.training.framework.exercises.ex3.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SecondView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	public SecondView() {
		addComponent(new Label("Second view initialized"));
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		addComponent(new Label("User navigated to the second view"));
	}
}
