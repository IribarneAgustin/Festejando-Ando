package com.microservice.festejandoando.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import com.microservice.festejandoando.model.Article;
import com.microservice.festejandoando.repository.IArticleRepository;
import com.microservice.festejandoando.utils.ArticleValidatorImpl;
import com.microservice.festejandoando.utils.ExceptionHandler;

@Service
public class ArticleService {

	@Autowired
	private IArticleRepository articleRepository;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ArticleValidatorImpl validator;

	public ResponseEntity<String> save(Article article) {
		ResponseEntity<String> response = null;
		try {
			Errors result = new BeanPropertyBindingResult(article, "article");
			validator.validate(article, result);
			if (!result.hasErrors()) {
				article.setActive(Boolean.TRUE);
				articleRepository.save(article);
				response = ResponseEntity.ok(messageSource.getMessage("article.saved.succesfully", null, null));
			} else {
				response = ExceptionHandler.handleErrors(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionHandler.internalServerErrorHandler(response);
		}

		return response;
	}

	public List<Article> findAll() {
		List<Article> result = new ArrayList<>();
		try {
			result = articleRepository.findByActiveTrue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResponseEntity<String> update(Long id, Article article) {
		ResponseEntity<String> response = validator.existsValidation(id);
		try {
			if (!response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				Errors result = new BeanPropertyBindingResult(article, "article");
				validator.validate(article, result);
				if (!result.hasErrors()) {
					article.setId(id);
					article.setActive(Boolean.TRUE);
					articleRepository.save(article);
					response = ResponseEntity.ok(messageSource.getMessage("article.updated.succesfully", null, null));
				} else {
					response = ExceptionHandler.handleErrors(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionHandler.internalServerErrorHandler(response);
		}
		return response;
	}

	public ResponseEntity<String> logicalDeletion(Long id, Article article) {
		ResponseEntity<String> response = validator.existsValidation(id);
		try {
			if (!response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				Errors result = new BeanPropertyBindingResult(article, "article");
				if (!result.hasErrors()) {
					article.setId(id);
					article.setActive(Boolean.FALSE);
					articleRepository.save(article);
					response = ResponseEntity.ok(messageSource.getMessage("article.deleted.succesfully", null, null));
				} else {
					response = ExceptionHandler.handleErrors(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionHandler.internalServerErrorHandler(response);
		}
		return response;
	}

	public ResponseEntity<String> markAsSuggestion(Long id, Article article) {
		ResponseEntity<String> response = validator.existsValidation(id);
		try {
			if (!response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				Errors result = new BeanPropertyBindingResult(article, "article");
				validator.validate(article, result);
				if (!result.hasErrors()) {
					article.setId(id);
					article.setActive(Boolean.TRUE);
					article.setSuggested(Boolean.TRUE);
					articleRepository.save(article);
					response = ResponseEntity.ok(messageSource.getMessage("article.updated.succesfully", null, null));
				} else {
					response = ExceptionHandler.handleErrors(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionHandler.internalServerErrorHandler(response);
		}
		return response;
	}

	public List<Article> findAllSuggestions() {
		List<Article> result = new ArrayList<>();
		try {
			result = articleRepository.findByActiveAndSuggested(Boolean.TRUE, Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResponseEntity<String> removeMarkAsSuggestion(Long id, Article article) {
		ResponseEntity<String> response = validator.existsValidation(id);
		try {
			if (!response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				Errors result = new BeanPropertyBindingResult(article, "article");
				validator.validate(article, result);
				if (!result.hasErrors()) {
					article.setId(id);
					article.setActive(Boolean.TRUE);
					article.setSuggested(Boolean.FALSE);
					articleRepository.save(article);
					response = ResponseEntity.ok(messageSource.getMessage("article.updated.succesfully", null, null));
				} else {
					response = ExceptionHandler.handleErrors(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionHandler.internalServerErrorHandler(response);
		}
		return response;
	}

	public List<Article> findSuggestedArticles() {
		List<Article> result = new ArrayList<>();
		try {
			result = articleRepository.findByActiveAndSuggested(Boolean.TRUE, Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Article> findByTopicId(Long topicId) {
		List<Article> result = new ArrayList<>();
		try {
			result = articleRepository.findByTopicId(topicId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Article> findSuggestedArticlesByTopic(Long topicId) {
		List<Article> result = new ArrayList<>();
		try {
			result = articleRepository.findArticlesByTopicIdAndSuggestedTrueAndActiveTrue(topicId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
