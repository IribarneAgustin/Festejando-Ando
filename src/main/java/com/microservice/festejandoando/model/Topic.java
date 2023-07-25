package com.microservice.festejandoando.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Topic")
public class Topic extends PersistentObject {

	private String name;
	private List<Long> suggestionsIds = new ArrayList<>();
    @Column(length = 2048)
	private List<String> images = new ArrayList<>();

	public Topic() {
	}

	public Topic(Long id, Boolean active, String name, ArrayList<Long> suggestionsIds, ArrayList<String> images) {
		super(id, active);
		this.name = name;
		this.suggestionsIds = suggestionsIds;
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Long> getSuggestionsIds() {
		return suggestionsIds;
	}

	public void setSuggestionsIds(ArrayList<Long> suggestionsIds) {
		this.suggestionsIds = suggestionsIds;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Topic [name=" + name + ", suggestions=" + suggestionsIds +", images=" + images + "]";
	}
	
}
