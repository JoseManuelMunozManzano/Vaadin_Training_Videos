package org.vaadin.training.views.department;

import java.util.List;

import org.vaadin.training.authentication.Department;
import org.vaadin.training.data.Person;
import org.vaadin.training.data.PersonListingDTO;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class DepartmentView extends VerticalSplitPanel implements View, EmployeeEditorHandler {

	/**
	 *
	 */
	private static final long serialVersionUID = -3226072479899520627L;

	public final static String VIEW_NAME = "department";

	private final DepartmentPresenter presenter;

	private Grid<PersonListingDTO> grid;

	private final EmployeeEditor employeeEditor = new EmployeeEditor(this);
	private final DepartmentViewHeader departmentInfo;

	public DepartmentView() {

		presenter = new DepartmentPresenter(this);

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
		grid.addColumn(PersonListingDTO::getFirstName).setCaption("First Name");
		grid.addColumn(PersonListingDTO::getLastName).setCaption("Last Name");
		grid.addColumn(PersonListingDTO::getEmail).setCaption("Email");

		grid.asSingleSelect().addValueChangeListener(event -> presenter.editRequested(event.getValue()));
	}

	public void openEditor(Person person) {
		if (person == null) {
			employeeEditor.setEnabled(false);

			Page.getCurrent().setUriFragment("!department", false);
			return;
		}

		employeeEditor.setEnabled(true);
		employeeEditor.setPerson(person);
		Page.getCurrent().setUriFragment("!department/" + person.getId(), false);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		presenter.viewEntered(event.getParameters());
	}

	public void updateDepartmentHeader(Department department) {
		departmentInfo.setDepartment(department);
	}

	public void updateEmployeeTable(List<PersonListingDTO> employees) {
		grid.setItems(employees);
	}

	@Override
	public void onSaveClick() {
		final Person person = employeeEditor.commit();
		presenter.requestSave(person);
	}

	@Override
	public void onCancelClick() {
		employeeEditor.discard();
	}

	public void select(PersonListingDTO person, int index) {
		grid.asSingleSelect().setValue(person);
		grid.scrollTo(index);
	}

}
