package com.vaadin.training.dataproviders.exercises.ex1;

import java.util.List;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

public class CreateDataProvider extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	final PersonService service = new PersonService();

	public CreateDataProvider() {

		final List<Person> persons = service.getAllPersons();

		final Grid<Person> grid = new Grid<>();
		grid.setWidth("100%");
		addComponent(grid);

		grid.setDataProvider(DataProvider.ofCollection(persons));

		grid.addColumn(Person::getName).setCaption("Name");
		grid.addColumn(Person::getEmail).setCaption("Email");
		grid.addColumn(Person::getAge).setCaption("Age");
		grid.addColumn(Person::getBirthday).setCaption("Birthday");
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}

}
