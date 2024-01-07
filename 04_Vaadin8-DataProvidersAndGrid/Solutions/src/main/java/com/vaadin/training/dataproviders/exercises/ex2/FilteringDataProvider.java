package com.vaadin.training.dataproviders.exercises.ex2;

import java.time.LocalDate;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class FilteringDataProvider extends CustomComponent implements View {

	private final ListDataProvider<Product> dataProvider;

	public FilteringDataProvider() {
		final VerticalLayout layout = new VerticalLayout();
		dataProvider = DataProviderHelper.createProductDataProvider();

		// create layout for DateFields
		final DateField fromField = new DateField();
		final DateField toField = new DateField();
		final Button filter = new Button("Filter");
		final HorizontalLayout filters = new HorizontalLayout(fromField, toField, filter);

		filter.addClickListener(e -> {

			// setFilter will clear any previous filter
			dataProvider.setFilter(product -> filterProduct(product, fromField.getValue(), toField.getValue()));
		});

		layout.addComponent(filters);

		// create and populate Grid
		final Grid<Product> grid = new Grid<>();
		grid.setDataProvider(dataProvider);

		grid.addColumn(Product::getAvailable).setCaption("Available");
		grid.addColumn(Product::getName).setCaption("Name");
		grid.addColumn(Product::getPrice).setCaption("Price");

		layout.addComponent(grid);

		setCompositionRoot(layout);
	}

	private boolean filterProduct(Product product, LocalDate start, LocalDate end) {

		final LocalDate available = product.getAvailable();

		// Null checks
		if (available == null) {

			if (start != null || end != null) {
				// data is null, but user wants to filter; assume false
				return false;
			}
			return true;
		}

		if (start == null && end == null) {

			return true;

		} else if (start == null) {

			return available.isBefore(end) || available.equals(end);

		} else if (end == null) {

			return available.isAfter(start) || available.equals(start);

		} else {

			final boolean atEnds = available.equals(start) || available.equals(end);
			final boolean inbetween = available.isAfter(start) && available.isBefore(end);

			return atEnds || inbetween;
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}
}
