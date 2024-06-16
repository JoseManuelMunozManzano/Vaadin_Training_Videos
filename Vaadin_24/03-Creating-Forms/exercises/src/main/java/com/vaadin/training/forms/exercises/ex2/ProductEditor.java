package com.vaadin.training.forms.exercises.ex2;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToDoubleConverter;

import java.util.function.Consumer;

public class ProductEditor extends Composite<VerticalLayout> implements HasComponents, HasSize{

    public ProductEditor(Product product, Consumer<Product> productConsumer){
        // TODO Create a new class that extends a layout for editing the product
        final ProductFormLayout productFormLayout = new ProductFormLayout();

        // TODO Create a Binder and bind it together with the input fields
        // on the editor component you created. Note that after the bindings
        // have been defined, you should have the binder read the Product bean
        // given as a parameter.
        final Binder<Product> binder = new Binder<>(Product.class);

        binder.forField(productFormLayout.getNameField())
                .bind("name");

        binder.forField(productFormLayout.getPriceField())
                .withConverter(new StringToDoubleConverter("Must enter a number"))
                .bind("price");

        binder.forField(productFormLayout.getAvailableField())
                .bind("available");

        binder.readBean(product);

        // TODO Create a Save button which will write the values from the binder
        // to the Product bean. A successful save should also refresh the
        // read-only view
        final HorizontalLayout hl = new HorizontalLayout();

        hl.add(new Button("Save", ev -> {
            try {
                binder.writeBean(product);
                productConsumer.accept(product);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }));

        // TODO Create a Cancel button which will read the values from the
        // Product bean to the binder
        hl.add(new Button("Cancel", ev -> binder.readBean(product)));

        add(productFormLayout, hl);
    }
}
