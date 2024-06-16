package com.vaadin.training.forms.exercises.ex2;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ProductFormLayout extends FormLayout {

    private final TextField nameField = new TextField("Name");
    private final TextField priceField = new TextField("Price");
    private final DatePicker availableField = new DatePicker("Available");

    public ProductFormLayout() {
        add(nameField, priceField, availableField);
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getPriceField() {
        return priceField;
    }

    public DatePicker getAvailableField() {
        return availableField;
    }
}
