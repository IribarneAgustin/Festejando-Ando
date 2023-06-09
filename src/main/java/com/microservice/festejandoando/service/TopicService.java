package com.microservice.festejandoando.service;

import java.util.List;
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

	public ResponseEntity<String> update(Long id, Topic topicDetails) {
		ResponseEntity<String> response = validator.existsValidation(id);
		try {
			// Optional<Topic> optionalTopic = topicRepository.findById(id);
			if (!response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				Errors result = new BeanPropertyBindingResult(topicDetails, "topic");
				validator.validate(topicDetails, result);
				if (!result.hasErrors()) {
					topicDetails.setId(id);
					topicDetails.setActive(Boolean.TRUE);
					topicRepository.save(topicDetails);
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
	/*
	 * if (optionalTopic.isPresent()) { Topic topic = optionalTopic.get();
	 * topic.setName(topicDetails.getName());
	 * topic.setSuggestionsIds(topicDetails.getSuggestionsIds());
	 * topic.setImages(topicDetails.getImages()); topicRepository.save(topic);
	 * response =
	 * ResponseEntity.ok(messageSource.getMessage("topic.updated.succesfully", null,
	 * null)); } else { response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
	 * .body(messageSource.getMessage("topic.updated.error", null, null)); } } catch
	 * (Exception e) { ExceptionHandler.internalServerErrorHandler(response); }
	 * return response; }
	 */

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
}
