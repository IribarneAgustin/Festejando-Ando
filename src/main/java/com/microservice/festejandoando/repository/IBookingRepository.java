package com.microservice.festejandoando.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservice.festejandoando.model.Booking;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long>{
    
}
