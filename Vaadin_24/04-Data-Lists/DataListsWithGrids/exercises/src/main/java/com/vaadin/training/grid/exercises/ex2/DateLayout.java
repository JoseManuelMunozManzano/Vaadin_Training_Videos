package com.vaadin.training.grid.exercises.ex2;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class DateLayout extends HorizontalLayout {

    private final DatePicker startDate = new DatePicker("Start");
    private final DatePicker endDate = new DatePicker("End");
    private final Button filterButton = new Button("Filter");

    public DateLayout() {
        setAlignItems(Alignment.END);
        add(startDate, endDate, filterButton);
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public Button getFilterButton() {
        return filterButton;
    }
}
