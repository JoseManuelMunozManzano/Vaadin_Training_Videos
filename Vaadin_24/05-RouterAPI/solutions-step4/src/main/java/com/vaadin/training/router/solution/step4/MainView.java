package com.vaadin.training.router.solution.step4;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

public class MainView extends Composite<VerticalLayout> implements HasComponents, RouterLayout, BeforeEnterObserver {

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
        menuBar.add(new RouterLink("Logout", LogoutRoute.class)); // Bonus: Logout with a Route
        
        // Bonus: Logout with an Anchor
        /*-
        Anchor logout = new Anchor("", "Logout");
        logout.getElement().addEventListener("click", e -> {
        	VaadinSession.getCurrent().getSession().invalidate();
        	UI.getCurrent().getPage().setLocation("loggedout");
        });
        menuBar.add(logout);
        -*/

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

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (VaadinSession.getCurrent().getAttribute("userLoggedIn") == null) {
            VaadinSession.getCurrent().setAttribute("intendedPath", event.getLocation().getPath());
            event.rerouteTo("login");
        }
    }

}
