package com.microservice.festejandoando.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.festejandoando.model.Article;
import com.microservice.festejandoando.service.ArticleService;

@CrossOrigin(origins = "${ALLOWED_ORIGINS}")
@RestController
@RequestMapping("/api/suggestion")
public class SuggestionController {

    @Autowired
    private ArticleService articleService;

    @PutMapping("/save/{id}")
    public ResponseEntity<String> save(@PathVariable Long id, @RequestBody Article article){
        return articleService.markAsSuggestion(id, article);
    }

    @GetMapping("/list")
    public List<Article> findAllSuggestions(){
        return articleService.findAllSuggestions();
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestBody Article article){
        return articleService.removeMarkAsSuggestion(id, article);
    }
    
}
