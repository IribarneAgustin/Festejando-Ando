package com.microservice.festejandoando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.festejandoando.model.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
	
	Boolean existsByIdAndActiveTrue(Long id);

	List<Client> findByActiveTrue();

	Client findByEmail (String email);
}
