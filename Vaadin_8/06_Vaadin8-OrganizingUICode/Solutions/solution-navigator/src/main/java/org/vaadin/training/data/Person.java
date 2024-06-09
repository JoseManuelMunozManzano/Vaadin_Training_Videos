package org.vaadin.training.data;

import org.vaadin.training.authentication.User;

public class Person {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private double salary;

	private User user;
	private String favouriteScifi;
	private int shoesize;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFavouriteScifi() {
		return favouriteScifi;
	}

	public void setFavouriteScifi(String favouriteScifi) {
		this.favouriteScifi = favouriteScifi;
	}

	public int getShoesize() {
		return shoesize;
	}

	public void setShoesize(int shoesize) {
		this.shoesize = shoesize;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
