package com.vaadin.training.forms.exercises.ex2;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public class ProductViewer extends Composite<FormLayout> implements HasSize{

    private final Paragraph productName;
    private final Paragraph productPrice;
    private final Paragraph productAvailable;

    public ProductViewer(Product product) {
        final FormLayout layout = getContent();
        final FlexLayout flex = new FlexLayout();

        flex.setFlexWrap(FlexLayout.FlexWrap.WRAP);

        //TODO adding paragraphs to formlayout to show product info
        Paragraph name = new Paragraph("Name");
        name.setWidth("50%");
        flex.add(name);

        productName = new Paragraph(product.getName());
        productName.setWidth("50%");
        flex.add(productName);

        Paragraph price = new Paragraph("Price");
        price.setWidth("50%");
        flex.add(price);

        productPrice = new Paragraph(String.valueOf(product.getPrice()));
        productPrice.setWidth("50%");
        flex.add(productPrice);

        Paragraph available = new Paragraph("Available");
        available.setWidth("50%");
        flex.add(available);

        productAvailable = new Paragraph(product.getAvailable().toString());
        productAvailable.setWidth("50%");
        flex.add(productAvailable);

        layout.add(flex);
    }

    void refresh(Product product){
        // TODO Update the three paragraphs with values from the Product bean.
        productName.setText(product.getName());
        productPrice.setText(String.valueOf(product.getPrice()));
        productAvailable.setText(product.getAvailable().toString());
    }
}
