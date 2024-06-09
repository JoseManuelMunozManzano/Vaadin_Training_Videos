package com.vaadin.training.dataproviders.exercises.ex3;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

public class BackEndDataProvider extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	final PersonService service = new PersonService();

	public BackEndDataProvider() {

		final List<AgeGroup> groups = new ArrayList<>();
		groups.add(new AgeGroup(0, 18));
		groups.add(new AgeGroup(19, 26));
		groups.add(new AgeGroup(27, 40));
		groups.add(new AgeGroup(41, 100));

		final ComboBox<AgeGroup> filter = new ComboBox<>("Filter", groups);
		addComponent(filter);

		final Grid<Person> grid = new Grid<>();
		grid.setWidth("100%");
		addComponent(grid);

		// create lazy Data Provider using the PersonService
		final CallbackDataProvider<Person, AgeGroup> dataProvider = DataProvider.fromFilteringCallbacks(
				q -> service.getPersons(q.getOffset(), q.getLimit(), q.getFilter().orElse(null)),
				q -> service.countPersons(q.getOffset(), q.getLimit(), q.getFilter().orElse(null)));

		final ConfigurableFilterDataProvider<Person, Void, AgeGroup> filterProvider = dataProvider
				.withConfigurableFilter();

		grid.setDataProvider(filterProvider);

		// add value change listener to filter and update the DataProvider
		// accordingly
		filter.addValueChangeListener(e -> {
			final AgeGroup value = e.getValue();
			filterProvider.setFilter(value);
		});

		grid.addColumn(Person::getName).setCaption("Name").setId("name");
		grid.addColumn(Person::getEmail).setCaption("Email").setId("email");
		grid.addColumn(Person::getAge).setCaption("Age").setId("age");
		grid.addColumn(Person::getBirthday).setCaption("Birthday").setId("birthday");
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}

}
