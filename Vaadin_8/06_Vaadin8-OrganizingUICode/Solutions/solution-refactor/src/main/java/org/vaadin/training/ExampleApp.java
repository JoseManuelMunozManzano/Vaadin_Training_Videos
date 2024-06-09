package org.vaadin.training;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("valo")
public class ExampleApp extends UI {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ExampleApp.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	public void init(VaadinRequest request) {

		setContent(new MainLayout());

	}

}
