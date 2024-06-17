package com.vaadin.training.grid.exercises.ex2;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
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
		// TODO create and populate Grid

	}

	private boolean filterProduct(Product product, LocalDate start, LocalDate end) {

		// TODO implement filtering logic here.
		return false;
	}

}
