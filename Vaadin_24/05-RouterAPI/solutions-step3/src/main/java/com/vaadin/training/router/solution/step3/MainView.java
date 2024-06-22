package com.vaadin.training.router.solution.step3;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

public class MainView extends Composite<VerticalLayout> implements HasComponents, RouterLayout {
    private final Div childWrapper = new Div();

    public MainView() {
        getContent().setSizeFull();

        H1 header = new H1("Header");
        add(header);

        HorizontalLayout mainContent = new HorizontalLayout();
        VerticalLayout menuBar = new VerticalLayout();
        menuBar.setWidth("20%");
        menuBar.add(new RouterLink("Home", HomeView.class));
        menuBar.add(new RouterLink("Lottery", LotteryView.class));
        mainContent.add(menuBar);

        mainContent.add(childWrapper);
        mainContent.setFlexGrow(1, childWrapper);

        add(mainContent);

        H1 footer = new H1("Footer");
        add(footer);

        getContent().setFlexGrow(1, mainContent);
        getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, header);
        getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, footer);
        getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH, mainContent);
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        childWrapper.getElement().appendChild(content.getElement());
    }
}
