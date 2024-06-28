package com.vaadin.training.theming.solutions.ex2;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.training.theming.solutions.MainLayout;

import java.io.Serial;

@Route(value = Exercise2.ROUTE, layout = MainLayout.class)
public class Exercise2 extends VerticalLayout {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String ROUTE = "ex2";
    public static final String TITLE = "Exercise 2";

    public Exercise2() {
        setSizeFull();

        createEmailFields();
        createCheckBoxes();
        createComboBoxes();
        createExampleTextSpan();
    }

    private void createEmailFields() {
        EmailField emailField = new EmailField();
        emailField.setLabel("Enter your email");

        EmailField confirmField = new EmailField();
        confirmField.setLabel("Confirm your email");

        add(new HorizontalLayout(emailField, confirmField));
    }

    private void createCheckBoxes() {
        Checkbox normalCheckbox = new Checkbox();
        normalCheckbox.setLabel("This is a normal checkbox");

        Checkbox styledCheckbox = new Checkbox();
        styledCheckbox.setLabel("Check me to change the style");
        styledCheckbox.setClassName("green-when-checked");

        // TODO style styledCheckbox so it's background turns green when it's checked

        add(new HorizontalLayout(normalCheckbox, styledCheckbox));
    }

    private void createComboBoxes() {
        ComboBox<String> normalCombo = new ComboBox<>();
        normalCombo.setItems("First", "Second", "Third");

        ComboBox<String> yellowBgColorCombo = new ComboBox<>();
        yellowBgColorCombo.setItems("First", "Second", "Third");
        yellowBgColorCombo.setClassName("yellowBg");
        yellowBgColorCombo.setOverlayClassName("yellowBg");

        add(new HorizontalLayout(normalCombo, yellowBgColorCombo));
    }

    private void createExampleTextSpan() {
        Span span = new Span("This is example text");

        span.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.FontWeight.BOLD,
                LumoUtility.Border.ALL,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.BorderColor.ERROR,
                LumoUtility.Margin.Top.LARGE,
                LumoUtility.Padding.MEDIUM
        );

        add(span);
    }

}