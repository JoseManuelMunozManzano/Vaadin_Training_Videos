package com.vaadin.training.grid.solutions;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.training.grid.solutions.ex1.CreateGrid;
import com.vaadin.training.grid.solutions.ex2.FilteringDataProvider;
import com.vaadin.training.grid.solutions.ex3.BackEndDataProvider;

public class MainLayout extends AppLayout {

    public MainLayout() {
        addToNavbar(new DrawerToggle());
        addToNavbar(new H2("Data provider Exercise"));

        final VerticalLayout menuBar = new VerticalLayout();
        menuBar.add(new RouterLink(CreateGrid.TITLE, CreateGrid.class));
        menuBar.add(new RouterLink(FilteringDataProvider.TITLE, FilteringDataProvider.class));
        menuBar.add(new RouterLink(BackEndDataProvider.TITLE, BackEndDataProvider.class));
        addToDrawer(menuBar);
    }
}
