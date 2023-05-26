package com.microservice.festejandoando.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.microservice.festejandoando.model.Administrator;

public interface IAdminRepository extends JpaRepository<Administrator,Long>{    

    public abstract Administrator findByUsername(String username);

    
}
