package com.microservice.festejandoando.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table (name = "Suggestion")
public class Suggestion extends PersistentObject{

	@OneToMany
	@JoinColumn(name = "suggestion_id")
	private ArrayList<Article> articles = new ArrayList <> ();

	public Suggestion() {
	}

	public Suggestion(Long id, Boolean active, ArrayList <Article> articles) {
		super(id, active);
		this.articles = articles;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Suggestion [articles=" + articles + "]";
	}
	
}
