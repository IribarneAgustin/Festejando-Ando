package com.microservice.festejandoando.model;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "Article")
public class Article extends PersistentObject {

	private String name;
	private String image;
	private Boolean suggested;
	@ManyToOne
	@JoinColumn(name = "topic_id")// foreing_key column name
	private Topic topic;
	private Integer quantity;
	

	public Article() {
	}


	public Article(Long id, Boolean active, String name, String image, Boolean suggested, Topic topic,
			Integer quantity) {
		super(id, active);
		this.name = name;
		this.image = image;
		this.suggested = suggested;
		this.topic = topic;
		this.quantity = quantity;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Boolean getSuggested() {
		return suggested;
	}


	public void setSuggested(Boolean suggested) {
		this.suggested = suggested;
	}


	public Topic getTopic() {
		return topic;
	}


	public void setTopic(Topic topic) {
		this.topic = topic;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
