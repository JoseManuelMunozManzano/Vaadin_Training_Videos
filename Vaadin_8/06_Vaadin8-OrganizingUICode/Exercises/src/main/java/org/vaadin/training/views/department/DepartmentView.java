package org.vaadin.training.views.department;

import java.util.List;

import org.vaadin.training.authentication.AuthenticationService;
import org.vaadin.training.backend.PersonService;
import org.vaadin.training.data.Person;

import com.vaadin.server.Page;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class DepartmentView extends VerticalSplitPanel implements EmployeeEditorHandler {

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

		// fetch data
		updateEmployeeTable();
		updateDepartmentHeader();
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

}
