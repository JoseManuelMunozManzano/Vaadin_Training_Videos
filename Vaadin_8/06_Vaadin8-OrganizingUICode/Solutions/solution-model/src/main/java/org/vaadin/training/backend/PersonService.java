package org.vaadin.training.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.vaadin.training.authentication.Department;
import org.vaadin.training.authentication.User;
import org.vaadin.training.data.Person;
import org.vaadin.training.data.PersonListingDTO;

public class PersonService {

	private static String[] firstName = { "James", "John", "Robert", "Michael", "William", "David", "Richard",
			"Charles", "Joseph", "Christopher", "Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer",
			"Maria", "Susan", "Margaret", "Dorothy", "Lisa" };

	private static String[] lastName = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson",
			"Moore", "Taylor", "Andreson", "Thomas", "Jackson", "White" };

	private static String[] shows = { "Star Trek", "Star Wars", "Stargate SG-1", "Babylon 5", "Firefly" };

	private static String[] departments = { "Services", "Marketing", "Sales", "R&D" };

	private static List<Person> persons;

	/**
	 * TODO create your DTOs here and return to presenter (exercise 3)
	 */
	public List<PersonListingDTO> getListingDTOs() {
		final List<Person> employees = getEmployees();

		final List<PersonListingDTO> list = employees.stream().map(PersonListingDTO::new).collect(Collectors.toList());

		return list;
	}

	public List<Person> getEmployees() {
		if (persons == null) {

			final Random r = new Random();

			persons = new ArrayList<Person>();
			for (int i = 0; i < 100; i++) {
				final Person person = new Person();
				person.setId(i);
				person.setFirstName(firstName[r.nextInt(firstName.length)]);
				person.setLastName(lastName[r.nextInt(lastName.length)]);
				person.setFavouriteScifi(shows[r.nextInt(shows.length)]);
				person.setShoesize(r.nextInt(10) + 35);
				person.setSalary(r.nextInt(50000) + 30000 + r.nextInt(100) / 100d);

				person.setEmail(person.getFirstName().toLowerCase() + "." + person.getLastName().toLowerCase()
						+ "@example.com");

				final Department dep = new Department();
				dep.setName(departments[r.nextInt(departments.length)]);
				final User u = new User();
				u.setUserName(person.getEmail());
				u.setDepartment(dep);

				person.setUser(u);

				persons.add(person);
			}
		}
		AuditLogService.addAuditLogEntry("Personel data fetched");
		return persons;
	}

	public Person getEmployee(int id) {
		if (persons == null) {
			return null;
		}

		return persons.stream().filter(p -> p.getId() == id).findAny().orElse(null);
	}

	public List<Department> getDepartments() {
		final List<Department> list = new ArrayList<>();
		for (final String department : departments) {
			final Department dep = new Department();
			dep.setName(department);
			list.add(dep);
		}
		return list;
	}

	public void save(Person person) {
		// This simulates the bean being saved. Do nothing.
		AuditLogService.addAuditLogEntry(person.getFirstName() + " " + person.getLastName() + "'s details updated");
	}

}
