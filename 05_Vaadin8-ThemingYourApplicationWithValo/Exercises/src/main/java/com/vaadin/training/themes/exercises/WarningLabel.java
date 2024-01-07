package com.vaadin.training.themes.exercises;

import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class WarningLabel extends Label {

	public WarningLabel(String text) {
		super(text);
		
		// TODO Add a style name to this Label
		addStyleName("v-label-warning");
	}
}
