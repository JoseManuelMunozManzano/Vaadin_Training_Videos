package com.vaadin.training.forms.exercises.ex3;


import com.vaadin.annotations.PropertyId;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class ProductFormLayout extends FormLayout{

  @PropertyId("name")
  final private TextField nameField = new TextField("Name");

  final private TextField priceField = new TextField("Price");

  @PropertyId("options")
  final private CheckBoxGroup<String> optionsCheckBoxGroup = new CheckBoxGroup<>("Options");

  final private DateField availableField = new DateField("Available Date");

  public ProductFormLayout() {
    optionsCheckBoxGroup.setItems("First", "Second", "Third");

    addComponents(nameField, priceField, optionsCheckBoxGroup, availableField);
  }

  public TextField getNameField() {
    return nameField;
  }

  public TextField getPriceField() {
    return priceField;
  }

  public DateField getAvailableField() {
    return availableField;
  }

  
}
