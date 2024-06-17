package com.vaadin.training.grid.exercises.ex1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonService {

	private static final String[] firstName = { "James", "John", "Robert", "Michael", "William", "David", "Richard",
			"Charles", "Joseph", "Christopher", "Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer",
			"Maria", "Susan", "Margaret", "Dorothy", "Lisa" };

	private static final String[] lastName = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson",
			"Moore", "Taylor", "Andreson", "Thomas", "Jackson", "White" };

	private static List<Person> persons;

	public List<Person> getAllPersons() {
		if (persons == null) {

			final Random r = new Random();

			persons = new ArrayList<>();
			for (int i = 0; i < 100; i++) {
				final Person person = new Person();
				person.setName(firstName[r.nextInt(firstName.length)] + " " + lastName[r.nextInt(lastName.length)]);
				person.setAge(r.nextInt(50) + 18);
				person.setEmail(person.getName().replaceAll(" ", ".") + "@example.com");

				LocalDate date = LocalDate.now().minusYears(person.getAge());
				date = date.withMonth(r.nextInt(12) + 1);
				date = date.withDayOfMonth(r.nextInt(28) + 1);
				person.setBirthday(date);

				persons.add(person);
			}
		}
		return persons;
	}

}
