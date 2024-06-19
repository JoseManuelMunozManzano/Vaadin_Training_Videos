package com.vaadin.training.grid.exercises.ex3;


import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.training.grid.exercises.MainLayout;
import com.vaadin.training.grid.exercises.ex1.Person;

import java.util.ArrayList;
import java.util.List;

@Route(value = BackEndDataProvider.ROUTE, layout = MainLayout.class)
public class BackEndDataProvider extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public static final String ROUTE = "ex3";
	public static final String TITLE = "Exercise 3";

	final PersonService service = new PersonService();

	public BackEndDataProvider() {
 		setWidth("100%");

		final List<AgeGroup> groups = new ArrayList<>();
		groups.add(new AgeGroup(0, 18));
		groups.add(new AgeGroup(19, 26));
		groups.add(new AgeGroup(27, 40));
		groups.add(new AgeGroup(41, 100));

		final ComboBox<AgeGroup> filter = new ComboBox<>("Filter", groups);
		add(filter);

		final Grid<Person> grid = new Grid<>();
		grid.setWidth("90%");
		add(grid);

		// TODO create lazy Data Provider using the PersonService
		final CallbackDataProvider<Person, AgeGroup> dataProvider = DataProvider.fromFilteringCallbacks(
				query -> service.getPersons(
						query.getOffset(),
						query.getLimit(),
						query.getFilter().orElse(null)
				),
				query -> service.countPersons(query.getOffset(),
							query.getLimit(),
							query.getFilter().orElse(null))
		);

		final ConfigurableFilterDataProvider<Person, Void, AgeGroup> filteredDataProvider =
				dataProvider.withConfigurableFilter();

		grid.setDataProvider(filteredDataProvider);

		// TODO add value change listener to filter and update the DataProvider
		// accordingly
		filter.addValueChangeListener(event -> filteredDataProvider.setFilter(event.getValue()));

		grid.addColumn(Person::getName).setHeader("Name").setKey("name");
		grid.addColumn(Person::getEmail).setHeader("Email").setKey("email");
		grid.addColumn(Person::getAge).setHeader("Age").setKey("age");
		grid.addColumn(Person::getBirthday).setHeader("Birthday").setKey("birthday");
	}

}
