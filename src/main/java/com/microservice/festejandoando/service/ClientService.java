package com.microservice.festejandoando.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.festejandoando.model.Client;
import com.microservice.festejandoando.repository.IClientRepository;

@Service
public class ClientService {
    
    @Autowired
    private IClientRepository clientRepository;

    public List<Client> findAll(){ 
        List<Client> result = new ArrayList<>();
        try{
            result = clientRepository.findByActiveTrue();
        }catch(Exception e){
            //TO DO logger
            e.printStackTrace();
        }
        return result;
    }
}
