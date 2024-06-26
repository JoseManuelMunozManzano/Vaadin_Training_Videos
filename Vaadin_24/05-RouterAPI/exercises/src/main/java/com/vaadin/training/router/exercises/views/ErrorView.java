package com.vaadin.training.router.exercises.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.training.router.exercises.errors.InvalidValueException;

public class ErrorView extends Div implements HasErrorParameter<InvalidValueException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<InvalidValueException> parameter) {
        setText("Ooops, seems it's an invalid number");
        return 500;
    }
}
