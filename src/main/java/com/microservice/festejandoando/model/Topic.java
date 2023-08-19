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
	private Integer suggestedQuantity;
	@Column(length = 2048)
	private String description;

	public Topic() {
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

	public void setSuggestionsIds(List<Long> suggestionsIds) {
		this.suggestionsIds = suggestionsIds;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Integer getSuggestedQuantity() {
		return suggestedQuantity;
	}

	public void setSuggestedQuantity(Integer suggestedQuantity) {
		this.suggestedQuantity = suggestedQuantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	
}
