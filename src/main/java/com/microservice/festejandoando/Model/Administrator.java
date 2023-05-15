package com.microservice.festejandoando.Model;

public class Administrator extends PersistentObject{

	private String username;
	private String password;
	
	public Administrator() {
	}
	public Administrator(Long id, Boolean active, String username, String password) {
		super(id, active);
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Administrator [username=" + username + ", password=" + password + "]";
	}
	
}
