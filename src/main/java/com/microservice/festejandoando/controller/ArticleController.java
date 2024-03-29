package com.microservice.festejandoando.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.festejandoando.model.Article;
import com.microservice.festejandoando.service.ArticleService;

@CrossOrigin(origins = "${ALLOWED_ORIGINS}")
@RestController
@RequestMapping("/api/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@GetMapping("/list")
	public List<Article> findAll() {
		return articleService.findAll();
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Article article) {
		return articleService.save(article);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Article article) {
		return articleService.update(id, article);
	}

	@PutMapping("/delete/{id}")
	public ResponseEntity<String> logicalDeletion(@PathVariable Long id, @RequestBody Article article) {
		return articleService.logicalDeletion(id, article);
	}

	@GetMapping("/list/suggested")
	public List<Article> findSuggestedArticles() {
		return articleService.findSuggestedArticles();
	}

	@GetMapping("/listByTopic/{id}")
	public List<Article> findByTopicId(@PathVariable Long id) {
		return articleService.findByTopicId(id);
	}

	@GetMapping("/listSuggestedArticlesByTopic/{id}")
	public List<Article> findSuggestedArticlesByTopic(@PathVariable Long id) {
		return articleService.findSuggestedArticlesByTopic(id);
	}


}
