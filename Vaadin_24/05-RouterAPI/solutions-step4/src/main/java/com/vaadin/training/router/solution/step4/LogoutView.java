package com.vaadin.training.router.solution.step4;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("loggedout")
@PageTitle("Logged out")
public class LogoutView extends Div  {

	public LogoutView() {
		add(new VerticalLayout(
				new Text("You're logged out now."),
				new RouterLink("Log in again", LoginView.class)
			));
	}

}
