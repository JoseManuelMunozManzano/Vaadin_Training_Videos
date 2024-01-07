package com.vaadin.training.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.util.CurrentInstance;

@Configuration
public class MenuConfiguration {

	@Bean
	@UIScope
	protected MainMenu configureMainMenu() {
		VaadinRequest vaadinRequest = CurrentInstance.get(VaadinRequest.class);
		String header = vaadinRequest.getHeader("user-agent");
		if (header.contains("Mobile")) {
			return new MobileMainMenu();
		}
		return new MainMenuBean();
	}
}
