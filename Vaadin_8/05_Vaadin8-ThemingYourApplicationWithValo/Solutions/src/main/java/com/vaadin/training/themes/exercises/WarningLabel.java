package com.vaadin.training.themes.exercises;

import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class WarningLabel extends Label {

	public WarningLabel(String text) {
		super(text);
		addStyleName("warning-label");
	}
}