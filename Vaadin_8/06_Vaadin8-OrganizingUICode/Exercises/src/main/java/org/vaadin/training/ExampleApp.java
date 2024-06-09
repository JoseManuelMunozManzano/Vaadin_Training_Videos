package org.vaadin.training;

import javax.servlet.annotation.WebServlet;

import org.vaadin.training.views.auditing.AuditingView;
import org.vaadin.training.views.department.DepartmentView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
public class ExampleApp extends UI implements ClickListener {
	private static final long serialVersionUID = 1L;

	private Button department;
	private Button auditing;

	private Panel panel;

	@SuppressWarnings("serial")
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ExampleApp.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	public void init(VaadinRequest request) {

		final HorizontalLayout rootLayout = new HorizontalLayout();
		rootLayout.setSizeFull();

		createNavigationBar(rootLayout);

		panel = new Panel();
		panel.setSizeFull();

		navigateToDefaultView();

		rootLayout.addComponent(panel);
		rootLayout.setExpandRatio(panel, 1);

		setContent(rootLayout);
	}

	private void navigateToDefaultView() {
		panel.setContent(new DepartmentView());
	}

	private void createNavigationBar(HorizontalLayout rootLayout) {
		final VerticalLayout sideNavigation = new VerticalLayout();
		sideNavigation.setWidth("200px");
		sideNavigation.setMargin(true);
		sideNavigation.setSpacing(true);

		department = new Button("Department", this);
		auditing = new Button("Auditing", this);
		sideNavigation.addComponent(department);
		sideNavigation.addComponent(auditing);

		rootLayout.addComponent(sideNavigation);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (department.equals(event.getButton())) {
			panel.setContent(new DepartmentView());
		} else if (auditing.equals(event.getButton())) {
			panel.setContent(new AuditingView());
		}
	}

}
