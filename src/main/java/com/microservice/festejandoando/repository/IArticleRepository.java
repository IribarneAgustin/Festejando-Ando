package com.microservice.festejandoando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.festejandoando.model.Article;

@Repository
public interface IArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findByActiveTrue();

	Boolean existsByIdAndActiveTrue(Long id);

	List<Article> findByActiveAndSuggested(Boolean active, Boolean suggested);

	List<Article> findByTopicId(Long topicId);
}
