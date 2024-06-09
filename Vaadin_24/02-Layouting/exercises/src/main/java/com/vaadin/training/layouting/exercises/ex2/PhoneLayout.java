package com.vaadin.training.layouting.exercises.ex2;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout.FormItem;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextField;

public class PhoneLayout extends FlexLayout {

  public static FormItem formItem = new FormItem();

  public PhoneLayout() {

    this.setWidthFull();
    this.setAlignItems(Alignment.END);

    TextField phone = new TextField();
    Checkbox noCall = new Checkbox("Do not call");

    this.expand(phone);

    this.add(phone, noCall);
  }
}
