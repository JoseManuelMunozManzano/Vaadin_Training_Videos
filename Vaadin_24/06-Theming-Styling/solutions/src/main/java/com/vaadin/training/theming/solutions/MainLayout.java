package com.vaadin.training.theming.solutions;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.training.theming.solutions.ex1.Exercise1;
import com.vaadin.training.theming.solutions.ex2.Exercise2;
import com.vaadin.training.theming.solutions.ex3.Exercise3;

public class MainLayout extends AppLayout {

    public MainLayout() {
        addToNavbar(new DrawerToggle());
        addToNavbar(new H2("Theming Exercise"));

        final VerticalLayout menuBar = new VerticalLayout();
        menuBar.add(new RouterLink(Exercise1.TITLE, Exercise1.class));
        menuBar.add(new RouterLink(Exercise2.TITLE, Exercise2.class));
        menuBar.add(new RouterLink(Exercise3.TITLE, Exercise3.class));
        addToDrawer(menuBar);
    }
}
