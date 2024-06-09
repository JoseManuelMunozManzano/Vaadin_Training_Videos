package org.vaadin.training.authentication;

public class Department {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || !(obj instanceof Department)) {
			return false;
		}
		return name.equals(((Department) obj).name);
	}

	@Override
	public int hashCode() {
		if (name != null) {
			return name.hashCode();
		}
		return super.hashCode();
	}

}
