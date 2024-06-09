package org.vaadin.training.views.department;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.training.authentication.AuthenticationService;
import org.vaadin.training.backend.PersonService;
import org.vaadin.training.data.Person;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class DepartmentView extends VerticalSplitPanel implements EmployeeEditorHandler, View {

	public final static String VIEW_NAME = "department";

	private final PersonService service = new PersonService();
	private Grid<Person> grid;

	private final EmployeeEditor employeeEditor = new EmployeeEditor(this);
	private final DepartmentViewHeader departmentInfo;
	private List<Person> employees;

	public DepartmentView() {
		setSizeFull();

		final VerticalLayout departmentInfoAndEmployeeTableLayout = new VerticalLayout();
		departmentInfoAndEmployeeTableLayout.setSizeFull();
		departmentInfoAndEmployeeTableLayout.setSpacing(true);

		departmentInfo = new DepartmentViewHeader();
		departmentInfoAndEmployeeTableLayout.addComponent(departmentInfo);

		createEmployeeTable();
		departmentInfoAndEmployeeTableLayout.addComponent(grid);
		departmentInfoAndEmployeeTableLayout.setExpandRatio(grid, 1f);
		addComponent(departmentInfoAndEmployeeTableLayout);
		addComponent(employeeEditor);
	}

	private void createEmployeeTable() {
		grid = new Grid<>();
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.addColumn(Person::getFirstName).setCaption("First Name");
		grid.addColumn(Person::getLastName).setCaption("Last Name");
		grid.addColumn(Person::getEmail).setCaption("Email");

		grid.asSingleSelect().addValueChangeListener(event -> {
			if (event.getValue() == null) {
				employeeEditor.setEnabled(false);
				Page.getCurrent().setUriFragment("!department", false);
				return;
			}

			employeeEditor.setEnabled(true);
			employeeEditor.setPerson(event.getValue());
			Page.getCurrent().setUriFragment("!department/" + event.getValue().getId(), false);
		});
	}

	private void updateDepartmentHeader() {
		departmentInfo.setDepartment(AuthenticationService.getLoggedInUser().getDepartment());
	}

	private void updateEmployeeTable() {
		employees = service.getEmployees();
		grid.setItems(employees);
	}

	@Override
	public void onSaveClick() {
		final Person person = employeeEditor.commit();
		if (person != null) {
			service.save(person);
			grid.asSingleSelect().setValue(null);
		}
	}

	@Override
	public void onCancelClick() {
		employeeEditor.discard();
	}

	@Override
	public void enter(ViewChangeEvent event) {

		// fetch data
		updateEmployeeTable();
		updateDepartmentHeader();

		// select user if there is an id in the parameters
		if (event.getParameters() != null && !event.getParameters().isEmpty()) {
			try {
				final Integer id = Integer.parseInt(event.getParameters());
				final Person person = service.getEmployee(id);
				grid.asSingleSelect().setValue(person);
				grid.scrollTo(employees.indexOf(person));
			} catch (final NumberFormatException e) {
				Logger.getLogger("DepartmentView").log(Level.WARNING,
						"Unable to parse ID from '" + event.getParameters() + "'");
			}
		}
	}

}
