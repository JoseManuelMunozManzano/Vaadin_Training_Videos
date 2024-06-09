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

		// TODO Create a Navigator instance and map it to the current UI
		// instance and use the contentWrapper as the rendering target
		navigator = new Navigator(UI.getCurrent(), contentWrapper);

		registerViews();

		// TODO Navigate to the initial view
		navigateTo("First");
	}

	private Layout createNavigationLayout() {
		HorizontalLayout layout = new HorizontalLayout();

		Button button1 = new Button("View 1");
		layout.addComponent(button1);
		Button button2 = new Button("View 2");
		layout.addComponent(button2);

		// TODO Add a ClickListener to button1. This should request to navigate
		// to FirstView
		button1.addClickListener(ev -> navigateTo("First"));
		
		// TODO Add a ClickListener to button2. This should request to navigate
		// to SecondView
		button2.addClickListener(ev -> navigateTo("Second"));

		layout.setSpacing(true);
		layout.setMargin(true);
		return layout;
	}

	private void registerViews() {
		// TODO Register the FirstView class as a view to the Navigator, using
		// an instance of FirstView
		navigator.addView("First", new FirstView());

		// TODO Register the SecondView class as a view to the Navigator, using
		// a class reference
		navigator.addView("Second", new SecondView());
	}

	public void navigateTo(String viewId) {
		// TODO Request the Navigator instance to navigate to the given viewId
		navigator.navigateTo(viewId);
	}
}
