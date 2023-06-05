package com.microservice.festejandoando.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.festejandoando.model.Client;
import com.microservice.festejandoando.repository.IClientRepository;

@Service
public class ClientService {

    @Autowired
    private IClientRepository clientRepository;

    
    public Optional<Client> findById(Long id){
        return  clientRepository.findById(id);
    }

    

    
    
}
