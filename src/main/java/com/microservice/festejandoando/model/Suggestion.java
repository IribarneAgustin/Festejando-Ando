package com.microservice.festejandoando.Model;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "Suggestion")
public class Suggestion extends PersistentObject{

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
