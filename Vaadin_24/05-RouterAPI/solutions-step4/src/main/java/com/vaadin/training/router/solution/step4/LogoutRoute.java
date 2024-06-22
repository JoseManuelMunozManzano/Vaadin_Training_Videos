package com.vaadin.training.router.solution.step4;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

//Bonus: Logout with a Route
@Route("logout")
public class LogoutRoute extends Div implements BeforeEnterObserver {

	@Override
    public void beforeEnter(BeforeEnterEvent event) {
        VaadinSession.getCurrent().getSession().invalidate();
    	UI.getCurrent().getPage().setLocation("loggedout");
    }
}
