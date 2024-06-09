package org.vaadin.training.views.department;

import org.vaadin.training.authentication.Department;
import org.vaadin.training.backend.PersonService;
import org.vaadin.training.data.Person;

import com.vaadin.annotations.PropertyId;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;

public class EmployeeEditor extends FormLayout implements ClickListener {

	private static final long serialVersionUID = 1L;

	private static final boolean showHiddenFields = true;

	private final Button commit;
	private final Button cancel;

	private final TextField firstName;
	private final TextField lastName;
	private final TextField email;
	private final TextField salary;

	@PropertyId("favouriteScifi")
	private final TextField scifi;

	private final ComboBox<Department> department;
	private final TextField shoesize;

	private final EmployeeEditorHandler editorHandler;
	private final Binder<Person> binder;

	private Person person;

	public EmployeeEditor(EmployeeEditorHandler editorHandler) {
		this.editorHandler = editorHandler;
		binder = new Binder<>(Person.class);

		setEnabled(false);
		setMargin(true);

		firstName = new TextField("First name");
		addComponent(firstName);

		lastName = new TextField("Last name");
		addComponent(lastName);

		email = new TextField("E-mail");
		addComponent(email);

		salary = new TextField("Salary");
		addComponent(salary);

		scifi = new TextField("Favourite Scifi Franchice");

		shoesize = new TextField("Shoe size");

		department = new ComboBox<>("Department");
		department.setItems(new PersonService().getDepartments());
		department.setItemCaptionGenerator(Department::getName);

		if (showHiddenFields) {
			addComponents(department, scifi, shoesize);
		}

		final HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);

		commit = new Button("Commit", this);
		buttonLayout.addComponent(commit);

		cancel = new Button("Cancel", this);
		buttonLayout.addComponent(cancel);
		addComponent(buttonLayout);

		// Salary is of double type, we need to let the Binder know we want a
		// converter
		binder.forField(salary).withConverter(new StringToDoubleConverter("Could not convert to a number"))
				.bind("salary");
		binder.forField(shoesize).withConverter(new StringToIntegerConverter("Could not convert to a number"))
				.bind("shoesize");
		// Department is nested property so direct binding doesn't work.
		binder.forField(department).bind(p -> p.getUser().getDepartment(), (p, d) -> p.getUser().setDepartment(d));

		// Rest of the fields are OK
		binder.bindInstanceFields(this);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (commit.equals(event.getButton())) {
			editorHandler.onSaveClick();
		} else {
			editorHandler.onCancelClick();
		}
	}

	public Person commit() {
		try {
			binder.writeBean(person);
			return person;
		} catch (final ValidationException e) {
			Notification.show("Validation error", Type.ERROR_MESSAGE);
			return null;
		}
	}

	public void discard() {
		binder.readBean(person);
	}

	public void setPerson(Person person) {
		this.person = person;
		discard();
	}

}
