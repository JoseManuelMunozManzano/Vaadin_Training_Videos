package com.vaadin.training.framework.exercises.ex3;

import com.vaadin.navigator.Navigator;
import com.vaadin.training.framework.exercises.ex3.views.FirstView;
import com.vaadin.training.framework.exercises.ex3.views.SecondView;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	private Panel contentWrapper;
	private Navigator navigator;

	public MainLayout() {
		setMargin(false);
		addComponent(createNavigationLayout());

		contentWrapper = new Panel();
		contentWrapper.setSizeFull();
		addComponent(contentWrapper);
		setExpandRatio(contentWrapper, 1);

		navigator = new Navigator(UI.getCurrent(), contentWrapper);
		registerViews();
		navigateTo("firstView");
	}

	private Layout createNavigationLayout() {
		HorizontalLayout layout = new HorizontalLayout();

		Button button1 = new Button("View 1");
		layout.addComponent(button1);
		Button button2 = new Button("View 2");
		layout.addComponent(button2);

		button1.addClickListener(event -> navigateTo("firstView"));
		button2.addClickListener(event -> navigateTo("secondView"));

		layout.setSpacing(true);
		layout.setMargin(true);
		return layout;
	}

	private void registerViews() {
		navigator.addView("firstView", new FirstView());
		navigator.addView("secondView", SecondView.class);
	}

	public void navigateTo(String viewId) {
		navigator.navigateTo(viewId);
	}
}