package com.microservice.festejandoando.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.microservice.festejandoando.model.Article;
import com.microservice.festejandoando.repository.IArticleRepository;
import com.microservice.festejandoando.repository.ITopicRepository;

@Component
public class ArticleValidatorImpl implements Validator {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private IArticleRepository articleRepository;
	@Autowired
	private ITopicRepository topicRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return Article.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Article article = (Article) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				messageSource.getMessage("field.required.error", new Object[] { "name" }, null));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "image",
				messageSource.getMessage("field.required.error", new Object[] { "image" }, null));
		topicValidation(article, errors);
	}
	
	private void topicValidation(Article article, Errors errors) {
        if (article.getTopic() != null) {
            if (!topicRepository.existsByIdAndActiveTrue(article.getTopic().getId())) {
                errors.rejectValue("topic", messageSource.getMessage("topic.notExists.error", new Object[] { "topic" }, null));
            }
        }
    }

	public ResponseEntity<String> existsValidation(Long id) {
		ResponseEntity<String> response = ResponseEntity.status(HttpStatus.OK).body(null);
		if (!articleRepository.existsByIdAndActiveTrue(id)) {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(messageSource.getMessage("article.deleted.error", null, null));
		}
		return response;
	}
}
