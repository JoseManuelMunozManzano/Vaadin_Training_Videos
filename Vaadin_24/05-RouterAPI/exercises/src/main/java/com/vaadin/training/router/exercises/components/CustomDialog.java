package com.vaadin.training.router.exercises.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CustomDialog extends Dialog {

    private final Button confirmButton = new Button("Confirm");
    private final Button cancelButton = new Button("Cancel");

    public CustomDialog() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(confirmButton, cancelButton);
        add(new Text("Are you sure you want to leave this page?"), horizontalLayout);
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
