package com.vaadin.training.router.exercises;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route("home")
@RouteAlias("")
public class HomeView extends Composite<Div> implements HasComponents {

    public HomeView() {
        add(new Span("Welcome to lottery!"));
    }
}
