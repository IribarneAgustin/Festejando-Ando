package com.microservice.festejandoando.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "Client")
public class Client extends PersistentObject {

	private String name;
	private String lastName;
	private String email;

	public Client() {
	}

	public Client(Long id, Boolean active, String name, String lastName, String email) {
		super(id, active);
		this.name = name;
		this.lastName = lastName;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public String toString() {
		return "Client [name=" + name + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
}
