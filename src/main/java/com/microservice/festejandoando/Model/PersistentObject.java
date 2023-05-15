package com.microservice.festejandoando.Model;

public abstract class PersistentObject {

	private Long id;
	private Boolean active;
	
	public PersistentObject() {
	}

	public PersistentObject(Long id, Boolean active) {
		this.id = id;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "PersistentObject [id=" + id + ", active=" + active + "]";
	}
}
