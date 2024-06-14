package com.vaadin.training.forms.solutions;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.training.forms.solutions.ex1.Validation;
import com.vaadin.training.forms.solutions.ex2.BindingForms;

import static com.vaadin.training.forms.solutions.ex1.Validation.TITLE;

public class MainLayout extends AppLayout {

    public MainLayout() {
        addToNavbar(new DrawerToggle());
        addToNavbar(new H2("Data Binding Exercise") );

        final VerticalLayout menuBar = new VerticalLayout();
        menuBar.add(new RouterLink(TITLE, Validation.class));
        menuBar.add(new RouterLink(BindingForms.TITLE, BindingForms.class));
        addToDrawer(menuBar);
    }
}
