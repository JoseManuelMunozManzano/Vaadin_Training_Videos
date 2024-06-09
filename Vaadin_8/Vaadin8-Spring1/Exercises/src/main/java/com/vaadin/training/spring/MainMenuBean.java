package com.vaadin.training.spring;

import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

@SpringComponent
@UIScope
public class MainMenuBean extends VerticalLayout implements MainMenu {

  private static class DefaultMenuItem extends Button implements MenuItem {
    public DefaultMenuItem(String caption, Resource icon) {
      super(caption, icon);
    }
  }

  @Override
  public MenuItem addMenuItem(String caption, Resource icon, String viewName) {
    DefaultMenuItem menuItem = new DefaultMenuItem(caption, icon);
    addComponent(menuItem);

    return menuItem;
  }
  
}
