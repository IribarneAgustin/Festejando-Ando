package com.microservice.festejandoando.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.microservice.festejandoando.model.Topic;
import com.microservice.festejandoando.repository.ITopicRepository;

@Component
public class TopicValidatorImpl implements Validator{
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ITopicRepository topicRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return Topic.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				messageSource.getMessage("field.required.error", new Object[] { "name" }, null));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "images",
				messageSource.getMessage("field.required.error", new Object[] { "images" }, null));
	}

	public ResponseEntity<String> existsValidation(Long id) {
		ResponseEntity<String> response = ResponseEntity.status(HttpStatus.OK).body(null);
		if (!topicRepository.existsByIdAndActiveTrue(id)) {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(messageSource.getMessage("topic.deleted.error", null, null));
		}
		return response;
	}

}
