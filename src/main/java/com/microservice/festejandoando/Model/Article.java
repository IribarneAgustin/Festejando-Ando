package com.microservice.festejandoando.Model;

public class Article extends PersistentObject {

	private String name;
	private String image;
	private Topic topic;
	

	public Article() {
	}

	public Article(Long id, Boolean active, String name, String image, Topic topic) {
		super(id, active);
		this.name = name;
		this.image = image;
		this.topic = topic;
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

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "Article [name=" + name + ", image=" + image + ", topic=" + topic + "]";
	}

}
