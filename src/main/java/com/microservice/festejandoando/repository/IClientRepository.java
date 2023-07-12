package com.microservice.festejandoando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.festejandoando.model.Client;

public interface IClientRepository extends JpaRepository<Client,Long>{
    Boolean existsByIdAndActiveTrue(Long id);
    List<Client> findByActiveTrue();
}
