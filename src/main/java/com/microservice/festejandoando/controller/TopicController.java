package com.microservice.festejandoando.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<String> save(@RequestBody Topic topic) {
		return topicService.save(topic);
	}

	@GetMapping("/list")
	public List<Topic> getAllTopics() {
		return topicService.findAll();
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateTopic(@PathVariable Long id, @RequestBody Topic topicDetails) {
		return topicService.updateTopic(id, topicDetails);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> logicalDeletion(@PathVariable Long id, @RequestBody Topic topic) {
		return topicService.logicalDeletion(id, topic);
	}
}
