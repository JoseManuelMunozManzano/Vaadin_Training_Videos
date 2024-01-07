package com.vaadin.training.dataproviders.exercises.ex2;

import java.time.LocalDate;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
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

		// TODO create layout for DateFields
		final HorizontalLayout hLayout = new HorizontalLayout();

		final DateField start = new DateField("Start2");
		start.setValue(LocalDate.now());
		final DateField end = new DateField("End");
		end.setValue(LocalDate.now());
		final Button filter = new Button("Filter", ev -> dataProvider.setFilter(p -> filterProduct(p, start.getValue(), end.getValue())));
		
		hLayout.addComponents(start, end, filter);
		hLayout.setComponentAlignment(filter,Alignment.BOTTOM_CENTER);

		// TODO create and populate Grid
		final Grid<Product> gridProducts = new Grid<>(Product.class);
		gridProducts.setDataProvider(dataProvider);

		layout.addComponents(hLayout, gridProducts);


		setCompositionRoot(layout);
	}

	private boolean filterProduct(Product product, LocalDate start, LocalDate end) {

		// TODO implement filtering logic here.
		final LocalDate available = product.getAvailable();

		if (available == null) {
			return false;
		}

		if (available.isAfter(start) && available.isBefore(end)) {
			return true;
		}

		return false;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}
}
