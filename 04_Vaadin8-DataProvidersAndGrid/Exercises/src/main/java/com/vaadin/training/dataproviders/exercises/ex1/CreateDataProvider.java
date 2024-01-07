package com.vaadin.training.dataproviders.exercises.ex1;

import java.util.List;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

public class CreateDataProvider extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	final PersonService service = new PersonService();

	public CreateDataProvider() {

		final List<Person> persons = service.getAllPersons();

		// TODO create a Grid
		// Si se indica el nombre de la clase.class en el constructor del Grid
		// no hace falta addColumn, pero el orden de las columnas puede ser cualquiera.
		//
		// final Grid<Person> grid = new Grid<>(Person.class);
		//
		// O sin indicar la clase, pero hay que hacer los addColumn. Más flexible.
		final Grid<Person> grid = new Grid<>();
		grid.addColumn(Person::getName).setCaption("Name");
		grid.addColumn(Person::getEmail).setCaption("Email");
		grid.addColumn(Person::getAge).setCaption("Age");
		grid.addColumn(Person::getBirthday).setCaption("Birthday");
		
		grid.setWidth("100%");
		addComponent(grid);

		// TODO create a DataProvider for the Grid
		ListDataProvider<Person> provider = DataProvider.ofCollection(persons);
		grid.setDataProvider(provider);
		// provider.refreshAll();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}

}
