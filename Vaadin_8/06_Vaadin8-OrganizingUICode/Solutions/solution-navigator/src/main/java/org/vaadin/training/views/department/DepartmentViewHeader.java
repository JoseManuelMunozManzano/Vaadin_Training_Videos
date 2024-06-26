package org.vaadin.training.views.department;

import org.vaadin.training.authentication.Department;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class DepartmentViewHeader extends HorizontalLayout {

	private final Label departmentName;

	private final ProgressBar loading;

	public DepartmentViewHeader() {

		departmentName = new Label();
		departmentName.setValue("Department: ");
		departmentName.setStyleName(ValoTheme.LABEL_H2);
		addComponent(departmentName);

		setWidth("100%");
		loading = new ProgressBar();
		loading.setCaption("Loading data, please hold...");
		loading.setVisible(false);
		loading.setWidth("200px");
		addComponent(loading);

		setComponentAlignment(departmentName, Alignment.MIDDLE_LEFT);
		setComponentAlignment(loading, Alignment.MIDDLE_RIGHT);
	}

	public void setDepartment(Department department) {
		departmentName.setValue("Department: " + department.getName());
	}

	public void setLoadingState(float state) {
		if (state == 1) {
			loading.setVisible(false);
		} else {
			loading.setValue(state);
			loading.setVisible(true);
		}
	}

}
