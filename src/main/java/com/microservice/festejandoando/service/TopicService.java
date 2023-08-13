package com.microservice.festejandoando.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.microservice.festejandoando.model.Topic;
import com.microservice.festejandoando.repository.ITopicRepository;
import com.microservice.festejandoando.utils.ExceptionHandler;
import com.microservice.festejandoando.utils.TopicValidatorImpl;

@Service
public class TopicService {

	@Autowired
	private ITopicRepository topicRepository;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private TopicValidatorImpl validator;

	public ResponseEntity<String> save(Topic topic) {
		ResponseEntity<String> response = null;
		try {
			Errors result = new BeanPropertyBindingResult(topic, "topic");
			validator.validate(topic, result);
			if (!result.hasErrors()) {
				topic.setActive(Boolean.TRUE);
				topicRepository.save(topic);
				response = ResponseEntity.ok(messageSource.getMessage("topic.saved.succesfully", null, null));
			} else {
				response = ExceptionHandler.handleErrors(result);
			}
		} catch (Exception e) {
			ExceptionHandler.internalServerErrorHandler(response);
		}

		return response;
	}

	public List<Topic> findAll() {
		List<Topic> result = new ArrayList<>();
		try {
			result = topicRepository.findByActiveTrue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResponseEntity<String> update(Long id, Topic topic) {
		ResponseEntity<String> response = validator.existsValidation(id);
		try {
			if (!response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				Errors result = new BeanPropertyBindingResult(topic, "topic");
				validator.validate(topic, result);
				if (!result.hasErrors()) {
					topic.setId(id);
					topic.setActive(Boolean.TRUE);
					topicRepository.save(topic);
					response = ResponseEntity.ok(messageSource.getMessage("topic.updated.succesfully", null, null));
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

	public ResponseEntity<String> logicalDeletion(Long id, Topic topic) {
		ResponseEntity<String> response = validator.existsValidation(id);
		try {
			if (!response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				Errors result = new BeanPropertyBindingResult(topic, "topic");
				if (!result.hasErrors()) {
					topic.setId(id);
					topic.setActive(Boolean.FALSE);
					topicRepository.save(topic);
					response = ResponseEntity.ok(messageSource.getMessage("topic.deleted.succesfully", null, null));
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

	public Topic findById(Long id) {
		Optional<Topic> topic = topicRepository.findById(id);
		return topic.isPresent() ? topic.get() : null;
	}
}
