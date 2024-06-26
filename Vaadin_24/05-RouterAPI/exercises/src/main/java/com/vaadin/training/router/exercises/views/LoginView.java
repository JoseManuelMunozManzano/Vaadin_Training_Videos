package com.vaadin.training.router.exercises.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("login")
public class LoginView extends Composite<VerticalLayout> implements HasComponents {

    private Button button;

    public LoginView() {
        button = new Button("Login",
            event -> {
                VaadinSession.getCurrent().setAttribute("userLoggedIn", true);
                Object intendedPath = VaadinSession.getCurrent().getAttribute("intendedPath");
                UI.getCurrent().navigate("");
            }
        );
        add(button);
    }

    public Button getButton() {
        return button;
    }
}
