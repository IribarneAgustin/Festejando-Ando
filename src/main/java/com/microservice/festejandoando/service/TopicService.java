package com.microservice.festejandoando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.festejandoando.model.Topic;
import com.microservice.festejandoando.repository.ITopicRepository;

@Service
public class TopicService {

	@Autowired
	private ITopicRepository topicRepository;

	public Topic save(Topic topic) {

		return topicRepository.save(topic);
	}

	public Optional<Topic> findbyId(Long id) {

		return topicRepository.findById(id);
	}

	public List<Topic> findAll() {

		return topicRepository.findAll();
	}

	public ResponseEntity<String> updateTopic(Long id, Topic topicDetails) {
		Optional<Topic> optionalTopic = topicRepository.findById(id);
		ResponseEntity<String> response;
		if (optionalTopic.isPresent()) {
			Topic topic = optionalTopic.get();
			topic.setName(topicDetails.getName());
			topic.setSuggestionsIds(topicDetails.getSuggestionsIds());
			topic.setImages(topicDetails.getImages());
			topicRepository.save(topic);
			response = ResponseEntity.ok().build();
		} else {
			String errorMessage = "Topic with ID " + id + " not found.";
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		}
		return response;
	}

	public ResponseEntity<Void> deleteById(Long id) {
		topicRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
