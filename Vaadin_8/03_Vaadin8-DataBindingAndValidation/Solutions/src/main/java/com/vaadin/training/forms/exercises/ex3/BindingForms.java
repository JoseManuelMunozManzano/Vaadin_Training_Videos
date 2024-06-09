package com.vaadin.training.forms.exercises.ex3;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vaadin.data.Binder;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValidationException;
import com.vaadin.data.ValueContext;
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
		setCompositionRoot(horizontalLayout);

		Product product = createProduct();

		horizontalLayout.addComponent(createEditLayout(product));
		horizontalLayout.addComponent(createViewLayout(product));
	}

	private Layout createEditLayout(Product product) {

		ProductFormLayout productEditLayout = new ProductFormLayout();

		// First we create a BeanBinder typed for the Product bean
		final Binder<Product> binder = new Binder<>(Product.class);

		// Bonus task: Binds the price field using the CurrencyConverter
		binder.forField(productEditLayout.getPriceField()).withConverter(new CurrencyConverter())
				.bind(Product::getPrice, Product::setPrice);

		// Date field needs manual binding due to conversion between Date and
		// LocalDate
		binder.forField(productEditLayout.getAvailableField()).withConverter(new LocalDateToDateConverter()).bind(Product::getAvailable, Product::setAvailable);

		// This binds the name and options fields using the @PropertyId
		// annotations in the ProductForm
		binder.bindInstanceFields(productEditLayout);

		// Reads the initial data from the Product bean
		binder.readBean(product);

		// Build a footer, add Save and Cancel buttons
		HorizontalLayout footer = new HorizontalLayout();

		footer.addComponent(new Button("Save", event -> {
			try {
				binder.writeBean(product);
				// Refreshes the read-only view on successful save
				refreshReadOnlyView(product);
			} catch (ValidationException e) {
				e.printStackTrace();
			}
		}));

		footer.addComponent(new Button("Cancel", event -> binder.readBean(product)));

		// Build a containing layout for the editor and the footer
		VerticalLayout editorContainer = new VerticalLayout();
		editorContainer.setSpacing(false);
		editorContainer.setMargin(false);
		editorContainer.addComponents(productEditLayout, footer);
		return editorContainer;
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

	private void refreshReadOnlyView(Product product) {
		nameLabel.setValue(product.getName());
		priceLabel.setValue(new CurrencyConverter().convertToPresentation(product.getPrice(), null));
		optionsLabel.setValue(formatOptionsToString(product.getOptions()));
		availableLabel.setValue(String.valueOf(product.getAvailable()));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}

	private static Product createProduct() {
		Product product = new Product();
		product.setName("Testproduct");
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