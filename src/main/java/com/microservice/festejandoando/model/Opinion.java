package com.microservice.festejandoando.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "Opinion")
public class Opinion extends PersistentObject{

	private String comments;
	private Boolean hidden;
	private Integer stars;
	
	public Opinion () {
	}
	
	public Opinion(String comments, Boolean hidden, Integer stars) {
		super();
		this.comments = comments;
		this.hidden = hidden;
		this.stars = stars;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

}
