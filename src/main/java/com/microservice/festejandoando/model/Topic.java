package com.microservice.festejandoando.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Topic")
public class Topic extends PersistentObject {

	private String name;
	private ArrayList<String> images = new ArrayList<>();

	public Topic() {
	}

	public Topic(Long id, Boolean active, String name, ArrayList<String> images) {
		super(id, active);
		this.name = name;
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Topic [name=" + name + ", suggestions=" + ", images=" + images + "]";
	}
	
}
