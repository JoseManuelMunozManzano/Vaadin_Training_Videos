package com.vaadin.training.layouting.exercises.ex2;

// import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.FormItem;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep.LabelsPosition;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.Route;
import com.vaadin.training.layouting.exercises.MainLayout;

@Route(value = UseFormLayout.ROUTE, layout = MainLayout.class)
public class UseFormLayout extends VerticalLayout {
    public static final String ROUTE = "ex2";
    public static final String TITLE = "Exercise 2";
    public static final FormLayout formLayout = new FormLayout();
    public static FormItem formItem = new FormItem();
    public static final PhoneLayout phoneLayout = new PhoneLayout();

    public UseFormLayout(){
        setSizeFull();

        formLayout.setResponsiveSteps(
            new ResponsiveStep("20em", 1, LabelsPosition.TOP),
            new ResponsiveStep("30em", 1),
            new ResponsiveStep("50em", 2)
        );
        
        TextField firstName = new TextField();
        // Dos formas posibles
        // Forma 1
        firstName.setWidth("100%");
        // Forma 2
        // firstName.setWidthFull();
        formLayout.addFormItem(firstName, "First Name");

        TextField lastName = new TextField();
        lastName.setWidthFull();
        formLayout.addFormItem(lastName, "Last Name");

        TextField email = new TextField();
        email.setWidthFull();
        // Dos formas posibles
        // Forma 1
        // formItem = formLayout.addFormItem(email, "Email");
        // formLayout.setColspan(formItem, 2);
        // Forma 2
        formLayout.addFormItem(email, "Email").getElement().setAttribute("colspan", "2");

        formItem = formLayout.addFormItem(phoneLayout, "Phone");
        formLayout.setColspan(formItem, 2);
        
        PasswordField password = new PasswordField();
        password.setWidthFull();
        formLayout.addFormItem(password, "Password");
        
        // Dos formas posibles
        // Forma 1
        formLayout.getElement().appendChild(ElementFactory.createBr());
        // Forma 2
        // formLayout.add(new Html("<br/>"));

        PasswordField passwordRepeat = new PasswordField();
        passwordRepeat.setWidthFull();
        formLayout.addFormItem(passwordRepeat, "Repeat Password");

        add(formLayout);
    }
}
