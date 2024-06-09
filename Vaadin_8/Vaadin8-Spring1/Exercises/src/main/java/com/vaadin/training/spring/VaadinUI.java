package com.vaadin.training.spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@SpringUI
public class VaadinUI extends UI {

  @Autowired
  private MainMenu mainMenu;

  @Override
  protected void init(VaadinRequest request) {
    setSizeFull();

    HorizontalLayout layout = new HorizontalLayout();
    layout.setSizeFull();
    layout.addComponent(mainMenu.asComponent());

    mainMenu.addMenuItem("First", VaadinIcons.ADJUST, "");
    mainMenu.addMenuItem("Second", VaadinIcons.WRENCH, "second");

    setContent(layout);
  }
  
}
