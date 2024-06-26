package com.vaadin.training.router.exercises.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Home")
@Route(value = "home", layout = MainView.class)
@RouteAlias(value = "/", layout = MainView.class)
public class HomeView extends Composite<Div> implements HasComponents {

    public HomeView() {
        add(new Span("Welcome to lottery!"));
    }
}
