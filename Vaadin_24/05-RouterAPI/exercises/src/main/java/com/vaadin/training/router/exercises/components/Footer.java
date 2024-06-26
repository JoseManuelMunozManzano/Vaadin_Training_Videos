package com.vaadin.training.router.exercises.components;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Footer extends VerticalLayout {

    private String text;

    public Footer(String text) {
        this.text = text;

        setPadding(false);
        setAlignItems(Alignment.CENTER);
        add(new H1(text));
    }

    public String getText() {
        return text;
    }
}
