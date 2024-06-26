package com.vaadin.training.router.exercises.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.training.router.exercises.views.HomeView;
import com.vaadin.training.router.exercises.views.LogoutView;
import com.vaadin.training.router.exercises.views.LotteryView;

public class MenuBar extends VerticalLayout {

    public MenuBar() {
        setPadding(false);
        setWidth("20%");
        add(new RouterLink("Home", HomeView.class));
        add(new RouterLink("Lottery", LotteryView.class));
        add(new RouterLink("Logout", LogoutView.class));
    }
}
