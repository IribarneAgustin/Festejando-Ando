package com.microservice.festejandoando.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.festejandoando.model.Topic;
import com.microservice.festejandoando.service.TopicService;

@RestController
@RequestMapping("topics")
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	@PostMapping("/save")
	public Topic createTopic(@RequestBody Topic topic) {
		return topicService.save(topic);
	}

	@GetMapping
	public List<Topic> getAllTopics() {
		return topicService.findAll();
	}
	
	@GetMapping("/{id}")
	public Object getTopicById(@PathVariable Long id) {
		return topicService.findbyId(id);
	}

	

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTopic(@PathVariable Long id, @RequestBody Topic topicDetails) {
		Optional<Topic> optionalTopic  = topicService.findbyId(id);
		if (optionalTopic.isPresent()) {
			 Topic topic = optionalTopic.get();
			topic.setName(topicDetails.getName());
			topic.setSuggestionsIds(topicDetails.getSuggestionsIds());
			topic.setImages(topicDetails.getImages());
			Topic updatedTopic = topicService.save(topic);
			return ResponseEntity.ok(updatedTopic);
		} else {
			String errorMessage = "Topic with ID " + id + " not found.";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTopicById(@PathVariable Long id) {
		topicService.deleteById(id);
		String successMessage = "El topic con ID " + id + " ha sido eliminado exitosamente.";
		return ResponseEntity.ok(successMessage);
	}
}
