package com.vaadin.training.spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class MainMenuBean extends VerticalLayout implements MainMenu {

	@Autowired
	private Navigator navigator;

	private static class DefaultMenuItem extends Button implements MenuItem {
		public DefaultMenuItem(String caption, Resource icon) {
			super(caption, icon);
		}
	}

	@Override
	public MenuItem addMenuItem(String caption, Resource icon, String viewName) {
		DefaultMenuItem menuItem = new DefaultMenuItem(caption, icon);
		addComponent(menuItem);
		menuItem.addClickListener(e -> navigator.navigateTo(viewName));
		return menuItem;
	}
}
