package com.microservice.festejandoando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservice.festejandoando.model.Topic;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long>{

	List<Topic> findByActiveTrue();
	Boolean existsByIdAndActiveTrue(Long id);
}
