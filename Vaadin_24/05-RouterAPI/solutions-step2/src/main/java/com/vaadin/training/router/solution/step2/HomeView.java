package com.vaadin.training.router.solution.step2;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("home")
@PageTitle("Home")
public class HomeView extends Composite<VerticalLayout> implements HasComponents {

	public HomeView() {
		add(new Span("Welcome to lottery!"));
	}
}
