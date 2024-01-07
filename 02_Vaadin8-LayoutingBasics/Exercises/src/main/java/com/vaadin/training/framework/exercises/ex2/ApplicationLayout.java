package com.vaadin.training.framework.exercises.ex2;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ApplicationLayout extends VerticalLayout implements View {

	private HorizontalLayout hLayout;

	public ApplicationLayout() {
		Label header = new Label("This is the header. My height is 150 pixels");
		header.setStyleName("header");
		header.setWidth("100%");
		header.setHeight("150px");

		hLayout = new HorizontalLayout();
		hLayout.setSizeFull();
		createLabelLayout();

		Label footer = new Label("This is the footer area. My height is 100 pixels");
		footer.setStyleName("footer");
		footer.setWidth("100%");
		footer.setHeight("100px");

		addComponent(header);
		addComponent(hLayout);
		addComponent(footer);

		setExpandRatio(hLayout, 1);
		setSizeFull();
		setSpacing(false);
		setMargin(false);
	}

	private void createLabelLayout() {
		Label navigation = new Label("This is the navigation area. My width is 25% of the window.");
		navigation.setStyleName("navigation");
		navigation.setSizeFull();
		
		Label content = new Label("This is the content area");
		content.setStyleName("content");
		content.setSizeFull();

		hLayout.setSpacing(false);
		hLayout.addComponents(navigation, content);
		hLayout.setExpandRatio(navigation,1);
		hLayout.setExpandRatio(content, 3);		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}

	/**
	 * Ignore this method for now.
	 * 
	 * @return
	 */
	private Button createButton() {
		NativeButton button = new NativeButton("Ignore");
		button.setHeight("200px");
		button.setWidth("200px");
		return button;
	}
}
