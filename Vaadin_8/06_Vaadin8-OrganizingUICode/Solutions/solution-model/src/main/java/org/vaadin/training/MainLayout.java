package org.vaadin.training;

import org.vaadin.training.views.auditing.AuditingView;
import org.vaadin.training.views.department.DepartmentView;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MainLayout extends HorizontalLayout implements ClickListener {

	private Button department;
	private Button auditing;

	private final Panel panel;
	private final Navigator navigator;

	public MainLayout() {

		setSizeFull();

		createNavigationBar();

		panel = new Panel();
		panel.setSizeFull();

		navigator = new Navigator(UI.getCurrent(), panel);
		navigator.addView(DepartmentView.VIEW_NAME, new DepartmentView());
		navigator.addView("", new DepartmentView());
		navigator.addView(AuditingView.VIEW_NAME, AuditingView.class);

		addComponent(panel);
		setExpandRatio(panel, 1);

	}

	private void createNavigationBar() {
		final VerticalLayout sideNavigation = new VerticalLayout();
		sideNavigation.setWidth("200px");
		sideNavigation.setMargin(true);
		sideNavigation.setSpacing(true);

		department = new Button("Department", this);
		auditing = new Button("Auditing", this);
		sideNavigation.addComponent(department);
		sideNavigation.addComponent(auditing);

		addComponent(sideNavigation);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (department.equals(event.getButton())) {
			navigator.navigateTo(DepartmentView.VIEW_NAME);
		} else if (auditing.equals(event.getButton())) {
			navigator.navigateTo(AuditingView.VIEW_NAME);
		}
	}
}
