package com.vaadin.training.grid.exercises.ex2;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.training.grid.exercises.MainLayout;

import java.time.LocalDate;

@Route(value = FilteringDataProvider.ROUTE, layout = MainLayout.class)
public class FilteringDataProvider extends Composite<VerticalLayout>{

	public static final String ROUTE = "ex2";
	public static final String TITLE = "Exercise 2";

	private final ListDataProvider<Product> dataProvider;

	public FilteringDataProvider() {
		final VerticalLayout layout = getContent();
		layout.setWidth("100%");

		dataProvider = DataProviderHelper.createProductDataProvider();

		// TODO create layout for DateFields
		final DateLayout dateLayout = new DateLayout();

		dateLayout.getFilterButton().addClickListener(
				e -> dataProvider.setFilter(
						product -> filterProduct(product,
							   			  dateLayout.getStartDate().getValue(),
							              dateLayout.getEndDate().getValue()
						)
				)
		);

		layout.setSpacing(false);

		// TODO create and populate Grid
		Grid<Product> grid = new Grid<>();
		grid.addColumn(new LocalDateRenderer<>(Product::getAvailable)).setHeader("Available");
		grid.addColumn(Product::getName).setHeader("Name");
		grid.addColumn(Product::getPrice).setHeader("Price");
		grid.setItems(dataProvider);

		layout.add(dateLayout, grid);
	}

	private boolean filterProduct(Product product, LocalDate start, LocalDate end) {

		// TODO implement filtering logic here.
		if (start == null && end == null) return true;
		if (start == null) return product.getAvailable().isBefore(end);
		if (end == null) return product.getAvailable().isAfter(start);

		return product.getAvailable().isBefore(end) && product.getAvailable().isAfter(start);
	}

}
