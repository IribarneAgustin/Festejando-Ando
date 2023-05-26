package com.microservice.festejandoando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
/*
	public Object findbyId(Long id) {

		return topicRepository.findById(id);
	}

	public List<Topic> findAll() {

		return topicRepository.findAll();
	}

	public void deleteById(Long id) {
		topicRepository.deleteById(id);

	}*/

}
