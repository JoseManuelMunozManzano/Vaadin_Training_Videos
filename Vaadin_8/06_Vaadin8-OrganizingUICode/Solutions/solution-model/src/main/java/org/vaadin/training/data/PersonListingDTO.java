package org.vaadin.training.data;

import java.util.Objects;

/**
 * Lightweight representation of the {@link Person} class.
 */
public class PersonListingDTO {

	private int id;
	private String firstName;
	private String lastName;
	private String email;

	public PersonListingDTO(Person person) {
		id = person.getId();
		firstName = person.getFirstName();
		lastName = person.getLastName();
		email = person.getEmail();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PersonListingDTO that = (PersonListingDTO) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
