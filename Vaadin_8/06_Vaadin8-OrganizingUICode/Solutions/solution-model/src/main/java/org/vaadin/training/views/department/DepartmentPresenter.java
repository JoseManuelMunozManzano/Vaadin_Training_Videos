package org.vaadin.training.views.department;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.training.authentication.AuthenticationService;
import org.vaadin.training.backend.PersonService;
import org.vaadin.training.data.Person;
import org.vaadin.training.data.PersonListingDTO;

public class DepartmentPresenter {

	private final DepartmentView view;

	private final PersonService service = new PersonService();
	private List<PersonListingDTO> employees;

	public DepartmentPresenter(DepartmentView view) {
		this.view = view;
	}

	public void requestSave(Person person) {
		if (person != null) {
			service.save(person);

			employees = service.getListingDTOs();
			view.updateEmployeeTable(employees);

			view.select(null, 0);
		}
	}

	public void viewEntered(String parameters) {

		employees = service.getListingDTOs();

		view.updateEmployeeTable(employees);
		view.updateDepartmentHeader(AuthenticationService.getLoggedInUser().getDepartment());

		if (parameters != null && !parameters.isEmpty()) {
			try {
				final Integer id = Integer.parseInt(parameters);
				final Person person = service.getEmployee(id);
				view.select(new PersonListingDTO(person), employees.indexOf(person));
			} catch (final NumberFormatException e) {
				Logger.getLogger("DepartmentView").log(Level.WARNING, "Unable to parse ID from '" + parameters + "'");
			}
		}
	}

	public void editRequested(PersonListingDTO person) {

		Person selected = null;
		if (person != null) {
			selected = service.getEmployee(person.getId());
		}
		view.openEditor(selected);
	}
}
