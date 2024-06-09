package com.vaadin.training.forms.exercises.ex3;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class BindingForms extends CustomComponent implements View {

	private final Label nameLabel = new Label();
	private final Label priceLabel = new Label();
	private final Label optionsLabel = new Label();
	private final Label availableLabel = new Label();

	public BindingForms() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();
		setCompositionRoot(horizontalLayout);

		Product product = createProduct();

		horizontalLayout.addComponent(createEditLayout(product));
		horizontalLayout.addComponent(createViewLayout(product));
	}

	private Layout createEditLayout(Product product) {

		// TODO Create a new class that extends a layout for editing the product
		ProductFormLayout productEditLayout = new ProductFormLayout();

		// TODO Create a Binder and bind it together with the input fields
		// on the editor component you created. Note that after the bindings
		// have been defined, you should have the binder read the Product bean
		// given as a parameter.
		final Binder<Product> binder = new Binder<>(Product.class);

		// Bonus task: Binds the price field using the CurrencyConverter
		binder.forField(productEditLayout.getPriceField())
			.withConverter(new CurrencyConverter())
			.bind(Product::getPrice, Product::setPrice);

		// Date field needs manual binding due to conversion between Date and
		// LocalDate
		binder.forField(productEditLayout.getAvailableField())
			.withConverter(new LocalDateToDateConverter())
			.bind(Product:: getAvailable, Product::setAvailable);

		// This binds the name and options fields using the @PropertyId
		// annotations in the ProductForm
		binder.bindInstanceFields(productEditLayout);

		// Reads the initial data from the Product bean
		binder.readBean(product);

		// TODO Create a Save button which will write the values from the binder
		// to the Product bean. A successful save should also refresh the
		// read-only view
		HorizontalLayout footer = new HorizontalLayout();
    Button save = new Button("Save");
		footer.addComponent(save);
		save.addClickListener(event -> {
			try {
				binder.writeBean(product);
				refreshReadOnlyView(product);				
			} catch (ValidationException e) {
				e.printStackTrace();
			}
			
		});
		
		// TODO Create a Cancel button which will read the values from the
		// Product bean to the binder
    Button cancel = new Button("Cancel");
		footer.addComponent(cancel);
		cancel.addClickListener(event -> binder.readBean(product));

		// Build a containing layout for the editor and the footer
		VerticalLayout editorContainer = new VerticalLayout();
		editorContainer.setSpacing(false);
		editorContainer.setMargin(false);
		editorContainer.addComponents(productEditLayout, footer);
		return editorContainer;
	}

	private void refreshReadOnlyView(Product product) {
		// TODO Update the four labels with values from the Product bean. For
		// the options field, you can use the formatOptionsToString method to
		// get a nicer-looking output.
		nameLabel.setValue(product.getName());
		priceLabel.setValue(new CurrencyConverter().convertToPresentation(product.getPrice(), null));
		optionsLabel.setValue(formatOptionsToString(product.getOptions()));
		availableLabel.setValue(String.valueOf(product.getAvailable()));
	}

	private Layout createViewLayout(Product product) {

		FormLayout layout = new FormLayout();

		nameLabel.setEnabled(false);
		nameLabel.setCaption("Name");

		priceLabel.setEnabled(false);
		priceLabel.setCaption("Price");

		optionsLabel.setEnabled(false);
		optionsLabel.setCaption("Options");

		availableLabel.setEnabled(false);
		availableLabel.setCaption("Available");

		layout.addComponents(nameLabel, priceLabel, optionsLabel, availableLabel);

		refreshReadOnlyView(product);

		return layout;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}

	private static Product createProduct() {
		Product product = new Product();
		product.setName("Testproductname");
		product.setOptions(new HashSet<String>(Arrays.asList("First")));
		product.setAvailable(Calendar.getInstance().getTime());
		return product;
	}

	public String formatOptionsToString(Set<String> options) {
		String presentation = "";
		if (options != null) {
			for (String o : options) {
				if (presentation.length() > 0) {
					presentation += ", ";
				}

				presentation += o.toString();
			}
		}
		return presentation;
	}
}
