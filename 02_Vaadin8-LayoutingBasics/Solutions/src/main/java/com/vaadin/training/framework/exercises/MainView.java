package com.vaadin.training.framework.exercises;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ResourceReference;
import com.vaadin.training.framework.exercises.ex1.NavigationBar;
import com.vaadin.training.framework.exercises.ex2.ApplicationLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	private Navigator navigator;

	public MainView(Navigator navigator) {
		this.navigator = navigator;
		setSpacing(true);

		int i = 1;
		createExerciseLink(i++, "Navigation bar", NavigationBar.class);
		createExerciseLink(i++, "Application layout", ApplicationLayout.class);

		Button ex3 = new Button("Ex " + i++ + ": Adding Navigator");
		Resource res = new ExternalResource("app://navigator");
		final ResourceReference rr = ResourceReference.create(res, this, "ex10");
		ex3.addClickListener(event -> Page.getCurrent().open(rr.getURL(), null));
		ex3.setStyleName(ValoTheme.BUTTON_LINK);
		addComponent(ex3);
	}

	private void createExerciseLink(int exNum, String caption, Class<? extends View> viewClass) {
		navigator.addView(caption.replace(" ", ""), viewClass);
		Button button = new Button("Ex " + exNum + ": " + caption,
				event -> navigator.navigateTo(event.getButton().getData().toString().replace(" ", "")));
		button.setData(caption);
		button.setStyleName(ValoTheme.BUTTON_LINK);

		addComponent(button);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, no need to do anything here.
	}

}
