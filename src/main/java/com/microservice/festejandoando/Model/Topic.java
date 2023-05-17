package com.microservice.festejandoando.Model;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "Topic")
public class Topic extends PersistentObject {

	private String name;
	private ArrayList<Suggestion> suggestions = new ArrayList<>();
	private ArrayList<String> images = new ArrayList<>();

	public Topic() {
	}

	public Topic(Long id, Boolean active, String name, ArrayList<Suggestion> suggestions, ArrayList<String> images) {
		super(id, active);
		this.name = name;
		this.suggestions = suggestions;
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(ArrayList<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}

	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Topic [name=" + name + ", suggestions=" + suggestions + ", images=" + images + "]";
	}
	
}
