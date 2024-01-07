package com.vaadin.training.framework.exercises.ex1;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

/**
 * This view should have four buttons in a layout aligned horizontally in the
 * following way.
 * 
 * +--------------------------------------------------------------------+
 * | [Btn1] [Btn2] [Btn3]                                        [Btn4] |
 * +--------------------------------------------------------------------+
 * 
 */
public class NavigationBar extends CustomComponent implements View {

	private static final long serialVersionUID = 1L;

	public NavigationBar() {
		setCompositionRoot(createNavigationLayout());
	}

	private Layout createNavigationLayout() {
		// TODO Create a layout
		// TODO Create four buttons and add them to the layout
		// TODO Align the buttons as instructed

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setWidth("100%");

		Button bt1 = new Button("Button 1");
		Button bt2 = new Button("Button 2");
		Button bt3 = new Button("Button 3");
		Button bt4 = new Button("Button 4");

		hLayout.addComponents(bt1, bt2, bt3, bt4);
		hLayout.setExpandRatio(bt4, 1);
		hLayout.setComponentAlignment(bt4, Alignment.TOP_RIGHT);
		hLayout.setSpacing(true);

		return hLayout;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		//Navigator method, ignore for now
	}
}
