package com.vaadin.training.router.exercises.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.training.router.exercises.components.Footer;
import com.vaadin.training.router.exercises.components.Header;
import com.vaadin.training.router.exercises.components.MenuBar;

public class MainView extends Composite<VerticalLayout> implements RouterLayout, HasComponents, BeforeEnterObserver {

    // Si pongo static y refresco la pantalla falla.
    private final Div childWrapper = new Div();

    public MainView() {

        getContent().setSizeFull();

        Header header = new Header("Header");
        add(header);

        final HorizontalLayout mainContent = new HorizontalLayout();
        MenuBar menuBar = new MenuBar();
        menuBar.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        mainContent.add(menuBar);

        mainContent.add(childWrapper);
        mainContent.setFlexGrow(1, childWrapper);
        mainContent.setAlignItems(FlexComponent.Alignment.CENTER);

        Footer footer = new Footer("Footer");
        add (mainContent, footer);

        getContent().setFlexGrow(1, mainContent);
//        getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, header);
//        getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, footer);
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
