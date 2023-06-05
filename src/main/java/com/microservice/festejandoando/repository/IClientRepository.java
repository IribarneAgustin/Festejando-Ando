package com.microservice.festejandoando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.festejandoando.model.Client;

public interface IClientRepository extends JpaRepository<Client,Long>{
    
}
