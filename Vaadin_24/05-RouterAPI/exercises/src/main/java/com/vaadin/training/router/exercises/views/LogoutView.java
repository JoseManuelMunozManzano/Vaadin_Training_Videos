package com.vaadin.training.router.exercises.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;

@Route("loggedout")
@PageTitle("Logged out")
public class LogoutView extends Div {

    public LogoutView() {
        add(new VerticalLayout(
                new Text("You're logged out now."),
                new RouterLink("Log in again", LoginView.class)
        ));
    }
}
