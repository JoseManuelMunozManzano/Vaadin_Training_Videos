package com.vaadin.training.framework.exercises.ex3;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class NavigatorUI extends UI {

	private MainLayout layout;

	@Override
	protected void init(VaadinRequest request) {
		layout = new MainLayout();
		layout.setSizeFull();
		setContent(layout);
	}

	@WebServlet(urlPatterns = "/navigator/*", name = "NavigatorUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
	public static class NavigatorUIServlet extends VaadinServlet {
	}
}