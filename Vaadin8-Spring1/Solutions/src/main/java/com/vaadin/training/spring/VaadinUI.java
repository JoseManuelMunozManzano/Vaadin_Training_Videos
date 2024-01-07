package com.vaadin.training.spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@SpringUI
public class VaadinUI extends UI {

	@Autowired
	private MainMenu mainMenu;

	@Autowired
	private AppViewDisplay viewDisplay;

	@Override
	protected void init(VaadinRequest request) {
		setSizeFull();

		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeFull();
		layout.addComponent(mainMenu.asComponent());
		viewDisplay.setSizeFull();

		layout.addComponent(viewDisplay);

		layout.setExpandRatio(mainMenu.asComponent(), 1);
		layout.setExpandRatio(viewDisplay, 2);

		mainMenu.addMenuItem("First", VaadinIcons.ADJUST, "");
		mainMenu.addMenuItem("Second", VaadinIcons.WRENCH, "second");

		setContent(layout);
	}
}
