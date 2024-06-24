package com.vaadin.training.router.exercises;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainView extends Composite<VerticalLayout> implements HasComponents {

    public MainView() {
        add(new RouterLink("Home", HomeView.class));
        add(new RouterLink("Lottery", LotteryView.class));
    }

}
