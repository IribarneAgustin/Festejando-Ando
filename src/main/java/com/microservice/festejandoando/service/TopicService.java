package com.microservice.festejandoando.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.microservice.festejandoando.model.Topic;
import com.microservice.festejandoando.repository.ITopicRepository;
import com.microservice.festejandoando.utils.ExceptionHandler;

@Service
public class TopicService {

	@Autowired
	private ITopicRepository topicRepository;
	@Autowired
	private MessageSource messageSource;

	public ResponseEntity<String> save(Topic topic) {
		ResponseEntity<String> response = null;
		try {
			topic.setActive(Boolean.TRUE);
			topicRepository.save(topic);
			response = ResponseEntity.ok(messageSource.getMessage("topic.saved.succesfully", null, null));
		} catch (Exception e) {
			ExceptionHandler.internalServerErrorHandler(response);
		}

		return response;
	}

	public List<Topic> findAll() {
		List<Topic> result = new ArrayList<>();
		try {
			result = topicRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResponseEntity<String> updateTopic(Long id, Topic topicDetails) {
		ResponseEntity<String> response = null;
		try {
			Optional<Topic> optionalTopic = topicRepository.findById(id);
			if (optionalTopic.isPresent()) {
				Topic topic = optionalTopic.get();
				topic.setName(topicDetails.getName());
				topic.setSuggestionsIds(topicDetails.getSuggestionsIds());
				topic.setImages(topicDetails.getImages());
				topicRepository.save(topic);
				response = ResponseEntity.ok(messageSource.getMessage("topic.updated.succesfully", null, null));
			} else {
				response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(messageSource.getMessage("topic.updated.error", null, null));
			}
		} catch (Exception e) {
			ExceptionHandler.internalServerErrorHandler(response);
		}
		return response;
	}

	public ResponseEntity<String> logicalDeletion(Long id, Topic topic) {
		ResponseEntity<String> response = null;
		try {
			Optional<Topic> optionalTopic = topicRepository.findById(id);
			if (optionalTopic.isPresent()) {
				topic.setId(id);
				topic.setActive(Boolean.FALSE);
				topicRepository.save(topic);
				response = ResponseEntity.ok(messageSource.getMessage("topic.deleted.succesfully", null, null));
			} else {
				response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(messageSource.getMessage("topic.deleted.error", null, null));
			}
		} catch (Exception e) {
			ExceptionHandler.internalServerErrorHandler(response);
		}

		return response;
	}

}
